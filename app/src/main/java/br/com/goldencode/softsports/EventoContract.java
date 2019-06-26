package br.com.goldencode.softsports;

import android.provider.BaseColumns;

public class EventoContract {

    public EventoContract(){}

    public static final class EventoEntry implements BaseColumns {

        //Tabela evento
        public static final String TABELA_EVENTO = "evento";

        //Colunas
        public final static String COD_EVENTO = "cod_evento";
        public static final String FK_ESPORTE = "cod_esporte" ;
        public final static String TITULO_EVENTO = "nome_evento";
        public final static String ESPORTE = "esporte";
        public final static String LOCAL = "local";
        public final static String DT_EVENTO = "dt_evento";
        public final static String HR_INICIO = "hr_inicio";
        public final static String HR_TERMINO = "hr_termino";
        public final static String DESCRICAO = "observacoes";
        public final static String TIMESTAMP = "timestap";

    }

}
