package br.com.sevenrtc;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class App {
    public static void main(String[] args) {
        App app = new App();

        final List<MeuTipo> minhaLista = Arrays.asList(
                new MeuTipo(1, "John"),
                new MeuTipo(2, "Doe"),
                new MeuTipo(3, "Snow"));

        app.chamarProcedure(minhaLista);
        app.exibirResultados();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "TESTE", "TESTE");
    }

    public void chamarProcedure(List<MeuTipo> minhaLista) {
        final String chamada = "{call PKG_TEST.MEU_PROCEDURE(?)}";
        try (Connection connection = getConnection();
             CallableStatement callableSt = connection.prepareCall(chamada)) {

            final ARRAY minhaTabela = toArray(minhaLista, connection);
            callableSt.setArray(1, minhaTabela);
            callableSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private STRUCT toStruct(MeuTipo meuTipo, StructDescriptor structDescriptor, Connection connection) throws SQLException {
        Object[] attributes = new Object[]{meuTipo.getMeuId(), meuTipo.getMeuNome()};
        return new STRUCT(structDescriptor, connection, attributes);
    }

    private ARRAY toArray(List<MeuTipo> minhaLista, Connection connection) throws SQLException {
        final STRUCT[] structArray = new STRUCT[minhaLista.size()];
        final ListIterator<MeuTipo> iterator = minhaLista.listIterator();
        final StructDescriptor structDescriptor = StructDescriptor.createDescriptor("MEU_TIPO", connection);
        while (iterator.hasNext()) {
            structArray[iterator.nextIndex()] = toStruct(iterator.next(), structDescriptor, connection);
        }
        final ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("MEU_TIPO_TABELA", connection);

        return new ARRAY(arrayDescriptor, connection, structArray);
    }

    public void exibirResultados() {
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT MESSAGE FROM LOG_TABLE")) {
                while (rs.next()) {
                    System.out.println(rs.getString("MESSAGE"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
