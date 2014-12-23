TesteOracleTypes
================

Solução para o problema: [*Chamar procedure PL/SQL com coleção de objetos*][1].

Setup
-----

1. Faça o download do [Driver JDBC][2] próprio para a sua versão do Oracle e inclua em `${basedir}/lib/` (testado com `ojdbc6.jar` versão **11.2.0.4**).
2. Ajuste a dependencia para o driver no `pom.xml`. Exemplo:

   ```xml
   <dependency>
       <groupId>com.oracle</groupId>
       <artifactId>ojdbc6</artifactId>
       <version>11.2.0.4</version>
       <scope>system</scope>
       <systemPath>${basedir}/lib/ojdbc6.jar</systemPath>
   </dependency>
```
3. Materialize o *schema* em um banco de dados oracle com o arquivo `schema.sql`. 
4. Ajuste o método `getConnection`da classe `br.com.sevenrtc.App` conforme os dados de conexão do seu banco. Exemplo:

   ```java
   return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "TESTE", "TESTE");
   ```

[1]: http://pt.stackoverflow.com/q/42748/100
[2]: http://www.oracle.com/technetwork/database/features/jdbc/index-091264.html
