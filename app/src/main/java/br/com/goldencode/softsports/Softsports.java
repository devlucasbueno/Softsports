package br.com.goldencode.softsports;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.goldencode.softsports.EventoContract.*;

public class Softsports extends SQLiteOpenHelper {

    private static final int  VERSAO_BANCO = 4;

    //Nome da base de dados
    private static final String NAME = "bdd_softsports";

    //Tabela softplayer
    private static final String TABELA_SOFTPLAYER = "softplayer";

    //Colunas
    private final static String COD_SOFTPLAYER = "cod_softplayer";
    private final static String NOME = "nome";
    private final static String SOBRENOME = "sobrenome";
    private final static String EMAIL = "email";
    private final static String SENHA = "senha";
    private final static String FT_PERFIL = "ft_perfil";

    //Tabela esporte
    private static final String TABELA_ESPORTE = "esporte";

    //Colunas
    private final static String COD_ESPORTE = "cod_esporte";
    private final static String NOME_ESPORTE = "nome_esporte";

    //Tabela evento
    private static final String TABELA_EVENTO = "evento";

    //Colunas
    private final static String COD_EVENTO = "cod_evento";
    private static final String FK_ESPORTE = "cod_esporte" ;
    private final static String TITULO_EVENTO = "nome_evento";
    private final static String ESPORTE = "esporte";
    private final static String LOCAL= "local";
    private final static String DT_EVENTO = "dt_evento";
    private final static String HR_INICIO = "hr_inicio";
    private final static String HR_TERMINO = "hr_termino";
    private final static String DESCRICAO = "observacoes";

    //Métodos
    public Softsports(Context context) {
        super(context, NAME, null, VERSAO_BANCO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //FOREIGN KEY(NOME_QUE_EU_QUERO_DAR_PRA_FK) REFERENCES nome_da_tabela(nome_da_pk)

        //Query esporte
        String QUERY_TABELA_ESPORTE = "CREATE TABLE " + TABELA_ESPORTE + "("
                + COD_ESPORTE + " INTEGER PRIMARY KEY, " + NOME_ESPORTE + " TEXT)";

        db.execSQL(QUERY_TABELA_ESPORTE);

        //Query softplayer
        String QUERY_TABELA_SOFTPLAYER = "CREATE TABLE " + TABELA_SOFTPLAYER + "("
                + COD_SOFTPLAYER + " INTEGER PRIMARY KEY, "
                + NOME + " TEXT, "
                + SOBRENOME + " TEXT, "
                + EMAIL + " TEXT, "
                + SENHA + " TEXT, "
                + FT_PERFIL + " BLOB, "
                + FK_ESPORTE + " INTEGER, "

                + " FOREIGN KEY (" + FK_ESPORTE + " ) " + " REFERENCES "
                + TABELA_ESPORTE + "(" + COD_ESPORTE + "))";

        db.execSQL(QUERY_TABELA_SOFTPLAYER);

        //Query evento
        String QUERY_TABELA_EVENTO = "CREATE TABLE " +

                EventoEntry.TABELA_EVENTO + "(" +
                EventoEntry.COD_EVENTO + " INTEGER PRIMARY KEY, " + COD_ESPORTE + " INTEGER, " +
                EventoEntry.TITULO_EVENTO + " TEXT, " +
                EventoEntry.ESPORTE + " TEXT, " +
                EventoEntry.LOCAL + " TEXT, " +
                EventoEntry.DT_EVENTO + " TEXT, " +
                EventoEntry.HR_INICIO + " TEXT, " +
                EventoEntry.HR_TERMINO + " TEXT, " +
                EventoEntry.DESCRICAO + " TEXT, " +
                EventoEntry.TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                " FOREIGN KEY (" + FK_ESPORTE + " ) " +
                " REFERENCES " + TABELA_ESPORTE + "(" + COD_ESPORTE + " ))";

        db.execSQL(QUERY_TABELA_EVENTO);

    }

    public SQLiteDatabase pegarDados(){
        SQLiteDatabase mDatabase = this.getReadableDatabase();
        return mDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + EventoEntry.TABELA_EVENTO);
        onCreate(db);

    }

    void inserirEsportes(Esporte esporte){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NOME_ESPORTE, esporte.getNome_esporte());

        db.insert(TABELA_ESPORTE, null , values);
    }

    boolean cadastrarSoftplayer(Usuario softplayer) {

        //Escrever na base de dados
        SQLiteDatabase bdd_softsports = this.getWritableDatabase();

        //Valores:

        ContentValues players = new ContentValues();

        players.put(NOME, softplayer.getNome());
        players.put(SOBRENOME, softplayer.getSobrenome());
        players.put(EMAIL, softplayer.getEmail());
        players.put(SENHA, softplayer.getSenha());
        players.put(FK_ESPORTE, softplayer.getCodEsporte());

        bdd_softsports.insert(TABELA_SOFTPLAYER, null, players);

        Cursor cur = bdd_softsports.rawQuery("SELECT COUNT(*) FROM softplayer", null);
        if (cur.getCount() > 0){

            bdd_softsports.close(); return true ;

        }
        else

            return false;

    }

    boolean login (String email, String senha)
    {
        SQLiteDatabase bdd_softsports = this.getReadableDatabase();
        Cursor c = bdd_softsports.rawQuery("select * from softplayer where email=? and senha=?;",new String[]{email,senha});
        if(c.getCount()>0) return true;
        else return false;
    }
    boolean verificaemail (String email)
    {
        SQLiteDatabase bdd_softsports = this.getReadableDatabase();
        Cursor c = bdd_softsports.rawQuery("select * from softplayer where email=?;",new String[]{email});
        if(c.getCount()> 0) return false;
        else return true;

    }

}