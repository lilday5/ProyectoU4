// $ANTLR 3.5.2 T.g 2025-03-31 09:07:45

import java.util.ArrayList;
import java.util.List;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class TParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALFABETICO", "CERRAR", "CREAR", 
		"FECHA", "FIN", "ID", "INICIO", "NUMERICO", "TABLA", "USAR", "WS"
	};
	public static final int EOF=-1;
	public static final int ALFABETICO=4;
	public static final int CERRAR=5;
	public static final int CREAR=6;
	public static final int FECHA=7;
	public static final int FIN=8;
	public static final int ID=9;
	public static final int INICIO=10;
	public static final int NUMERICO=11;
	public static final int TABLA=12;
	public static final int USAR=13;
	public static final int WS=14;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public TParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public TParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return TParser.tokenNames; }
	@Override public String getGrammarFileName() { return "T.g"; }


	    List<Tabla> tablas = new ArrayList<Tabla>();
	    Tabla tablaActual = null;



	// $ANTLR start "inicio"
	// T.g:13:1: inicio : creacion usar ( tabla )+ cerrar ;
	public final void inicio() throws RecognitionException {
		try {
			// T.g:13:10: ( creacion usar ( tabla )+ cerrar )
			// T.g:13:12: creacion usar ( tabla )+ cerrar
			{
			pushFollow(FOLLOW_creacion_in_inicio24);
			creacion();
			state._fsp--;

			pushFollow(FOLLOW_usar_in_inicio26);
			usar();
			state._fsp--;

			// T.g:13:26: ( tabla )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==TABLA) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// T.g:13:26: tabla
					{
					pushFollow(FOLLOW_tabla_in_inicio28);
					tabla();
					state._fsp--;

					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			pushFollow(FOLLOW_cerrar_in_inicio31);
			cerrar();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "inicio"



	// $ANTLR start "creacion"
	// T.g:15:1: creacion : CREAR ID ;
	public final void creacion() throws RecognitionException {
		Token ID1=null;

		try {
			// T.g:15:10: ( CREAR ID )
			// T.g:15:12: CREAR ID
			{
			match(input,CREAR,FOLLOW_CREAR_in_creacion39); 
			ID1=(Token)match(input,ID,FOLLOW_ID_in_creacion41); 

			              System.out.println("CREATE DATABASE " + (ID1!=null?ID1.getText():null));
			          
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "creacion"



	// $ANTLR start "usar"
	// T.g:19:1: usar : USAR ID ;
	public final void usar() throws RecognitionException {
		Token ID2=null;

		try {
			// T.g:19:10: ( USAR ID )
			// T.g:19:12: USAR ID
			{
			match(input,USAR,FOLLOW_USAR_in_usar55); 
			ID2=(Token)match(input,ID,FOLLOW_ID_in_usar57); 

			              System.out.println("USE DATABASE " + (ID2!=null?ID2.getText():null));
			          
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "usar"



	// $ANTLR start "tabla"
	// T.g:23:1: tabla : TABLA ID INICIO ( campo )+ FIN ;
	public final void tabla() throws RecognitionException {
		Token ID3=null;

		try {
			// T.g:23:10: ( TABLA ID INICIO ( campo )+ FIN )
			// T.g:23:12: TABLA ID INICIO ( campo )+ FIN
			{
			match(input,TABLA,FOLLOW_TABLA_in_tabla70); 
			ID3=(Token)match(input,ID,FOLLOW_ID_in_tabla72); 
			match(input,INICIO,FOLLOW_INICIO_in_tabla74); 

			              System.out.println("CREATE TABLE " + (ID3!=null?ID3.getText():null));
			              System.out.println(" (" + (ID3!=null?ID3.getText():null) + "_key INTEGER AUTOINCREMENT NOT NULL");

			              Tabla t = new Tabla();
			              t.nombre = (ID3!=null?ID3.getText():null);
			              tablas.add(t);
			              tablaActual = t;
			          
			// T.g:32:11: ( campo )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==ID) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// T.g:32:11: campo
					{
					pushFollow(FOLLOW_campo_in_tabla88);
					campo();
					state._fsp--;

					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			match(input,FIN,FOLLOW_FIN_in_tabla101); 

			              System.out.println("   );   ");
			          
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "tabla"



	// $ANTLR start "campo"
	// T.g:37:1: campo : id1= ID (tipo1= NUMERICO |tipo2= ALFABETICO |tipo3= FECHA |ref= ID ) ;
	public final void campo() throws RecognitionException {
		Token id1=null;
		Token tipo1=null;
		Token tipo2=null;
		Token tipo3=null;
		Token ref=null;

		try {
			// T.g:38:3: (id1= ID (tipo1= NUMERICO |tipo2= ALFABETICO |tipo3= FECHA |ref= ID ) )
			// T.g:38:5: id1= ID (tipo1= NUMERICO |tipo2= ALFABETICO |tipo3= FECHA |ref= ID )
			{
			id1=(Token)match(input,ID,FOLLOW_ID_in_campo115); 
			// T.g:38:12: (tipo1= NUMERICO |tipo2= ALFABETICO |tipo3= FECHA |ref= ID )
			int alt3=4;
			switch ( input.LA(1) ) {
			case NUMERICO:
				{
				alt3=1;
				}
				break;
			case ALFABETICO:
				{
				alt3=2;
				}
				break;
			case FECHA:
				{
				alt3=3;
				}
				break;
			case ID:
				{
				alt3=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}
			switch (alt3) {
				case 1 :
					// T.g:38:13: tipo1= NUMERICO
					{
					tipo1=(Token)match(input,NUMERICO,FOLLOW_NUMERICO_in_campo120); 
					}
					break;
				case 2 :
					// T.g:38:30: tipo2= ALFABETICO
					{
					tipo2=(Token)match(input,ALFABETICO,FOLLOW_ALFABETICO_in_campo126); 
					}
					break;
				case 3 :
					// T.g:38:49: tipo3= FECHA
					{
					tipo3=(Token)match(input,FECHA,FOLLOW_FECHA_in_campo132); 
					}
					break;
				case 4 :
					// T.g:38:63: ref= ID
					{
					ref=(Token)match(input,ID,FOLLOW_ID_in_campo138); 
					}
					break;

			}


			      if (tipo1 != null) {
			         System.out.println(", " + (id1!=null?id1.getText():null) + " " + (tipo1!=null?tipo1.getText():null) );

			         Atributo a  = new Atributo();
			         a.nombreAtributo = (id1!=null?id1.getText():null);
			         a.tipoAtributo = (tipo1!=null?tipo1.getText():null);
			         tablaActual.atributos.add(a);
			      } else if (tipo2 != null) {
			         System.out.println(", " + (id1!=null?id1.getText():null) + " VARCHAR(300)" );

			         Atributo a  = new Atributo();
			         a.nombreAtributo = (id1!=null?id1.getText():null);
			         a.tipoAtributo = (tipo2!=null?tipo2.getText():null);
			         tablaActual.atributos.add(a);
			      } else if (tipo3 != null) {
			         System.out.println(", " + (id1!=null?id1.getText():null) + " DATE" );

			         Atributo a  = new Atributo();
			         a.nombreAtributo = (id1!=null?id1.getText():null);
			         a.tipoAtributo = (tipo3!=null?tipo3.getText():null);
			         tablaActual.atributos.add(a);
			      } else if (ref != null) {
			         System.out.println(", " + (id1!=null?id1.getText():null) + "_id INTEGER REFERENCES " + (ref!=null?ref.getText():null) + "(" + (ref!=null?ref.getText():null) + "_key)");

			         Atributo a  = new Atributo();
			         a.nombreAtributo = (id1!=null?id1.getText():null);
			         a.tipoAtributo = "ref(" + (ref!=null?ref.getText():null) + ")";
			         tablaActual.atributos.add(a);
			      }
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "campo"



	// $ANTLR start "cerrar"
	// T.g:71:1: cerrar : CERRAR ;
	public final void cerrar() throws RecognitionException {
		try {
			// T.g:71:10: ( CERRAR )
			// T.g:71:12: CERRAR
			{
			match(input,CERRAR,FOLLOW_CERRAR_in_cerrar155); 

			              for (int i = 0; i < tablas.size(); i++) {
			                  System.out.println("\nTabla: " + tablas.get(i).nombre);
			                  List<Atributo> atribs = tablas.get(i).atributos;
			                  for (int j = 0; j < atribs.size(); j++) {
			                      System.out.print("<Atributo>  " + atribs.get(j).nombreAtributo);
			                      System.out.println(" \t<TipoAtrib> " + atribs.get(j).tipoAtributo);
			                  }
			              }
			          
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "cerrar"

	// Delegated rules



	public static final BitSet FOLLOW_creacion_in_inicio24 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_usar_in_inicio26 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_tabla_in_inicio28 = new BitSet(new long[]{0x0000000000001020L});
	public static final BitSet FOLLOW_cerrar_in_inicio31 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CREAR_in_creacion39 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ID_in_creacion41 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_USAR_in_usar55 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ID_in_usar57 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TABLA_in_tabla70 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ID_in_tabla72 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_INICIO_in_tabla74 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_campo_in_tabla88 = new BitSet(new long[]{0x0000000000000300L});
	public static final BitSet FOLLOW_FIN_in_tabla101 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_campo115 = new BitSet(new long[]{0x0000000000000A90L});
	public static final BitSet FOLLOW_NUMERICO_in_campo120 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ALFABETICO_in_campo126 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FECHA_in_campo132 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_campo138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CERRAR_in_cerrar155 = new BitSet(new long[]{0x0000000000000002L});
}
