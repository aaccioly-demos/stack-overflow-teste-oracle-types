--------------------------------------------------------
--  File created - Tuesday-December-23-2014   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type MEU_TIPO
--------------------------------------------------------

  CREATE OR REPLACE TYPE "TESTE"."MEU_TIPO" AS OBJECT (
  meu_id       NUMBER(6),
  meu_nome     VARCHAR2(200)
);

/
--------------------------------------------------------
--  DDL for Type MEU_TIPO_TABELA
--------------------------------------------------------

  CREATE OR REPLACE TYPE "TESTE"."MEU_TIPO_TABELA" AS TABLE OF meu_tipo;

/
--------------------------------------------------------
--  DDL for Table LOG_TABLE
--------------------------------------------------------

  CREATE TABLE "TESTE"."LOG_TABLE" 
   (	"MESSAGE" CLOB
   ) ;
--------------------------------------------------------
--  DDL for Package PKG_TEST
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "TESTE"."PKG_TEST" AS 

PROCEDURE meu_procedure(tabela in meu_tipo_tabela);

END pkg_test;

/
--------------------------------------------------------
--  DDL for Package Body PKG_TEST
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE BODY "TESTE"."PKG_TEST" 
AS
  PROCEDURE meu_procedure(
      tabela IN meu_tipo_tabela)
  IS
    var_meu_tipo meu_tipo;
  BEGIN
    DELETE FROM log_table;
    FOR elem IN 1 .. tabela.count
    LOOP
      var_meu_tipo := tabela(elem);
      INSERT
      INTO log_table
        (
          MESSAGE
        )
        VALUES
        (
          var_meu_tipo.meu_id
          || ' - '
          || var_meu_tipo.meu_nome
        );
    END LOOP;
  END;
END pkg_test;

/
