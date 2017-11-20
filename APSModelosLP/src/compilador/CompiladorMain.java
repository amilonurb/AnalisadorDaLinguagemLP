/**
 * 126 - MODELOS DE LINGUAGEM DE PROGRAMA��O - 2017.2
 * ATIVIDADE PR�TICA SUPERVISIONADA
 * @author Bruno Rodrigues Lima - 2016101533
 * 
 * ANALISADOR DA LINGUAGEM LP
 * FAZ A AN�LISE L�XICA E SINT�TICA B�SICA DA LINGUAGEM ESPECIFICADA
 *
 *
 * =========================== BNF =============================
 * 
 * REGRAS L�XICAS:
 * <OP>-> '+' | '-' | '*' | '/' | '=' | '!=' | '>' | '<' | '>=' | '<='
 * <ID>-> <LETTER> <LD>*
 * <LD>-> <LETTER> | <DIGIT>
 * <NUMBER>-> <DIGIT>+
 * <LETTER>-> 'a' | 'b' | ... | 'z'
 * <DIGIT>-> '0' | '1' | ... | '9'
 * 
 * REGRAS SINT�TICAS:
 * <PROGRAM>-> 'program' '{' <STATEMENT>* '}'
 * <STATEMENT>-> <ASSIGNMENT> | <CONDITIONAL> | <LOOP>
 * <ASSIGNMENT>-> <ID> '=' <EXPR> ';'
 * <CONDITIONAL>-> 'if' <EXPR> '{' <STATEMENT>+ '}' | 
 * 				  'if' <EXPR> '{' <STATEMENT>+ '}' 'else' '{' <STATEMENT>+ '}'
 * <LOOP>-> 'while' <EXPR> '{' <STATEMENT>+ '}'
 * <EXPR>-> <ID> | <NUMBER> | '(' <EXPR> ')' | <EXPR> <OP> <EXPR>
 * 
 * =========================== BNF =============================
 */

package compilador;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import compilador.view.FrmCompilador;

public class CompiladorMain {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FrmCompilador frame = new FrmCompilador();
				frame.setVisible(true);
			} catch (Exception exception) {
				exception.printStackTrace();
				JOptionPane.showMessageDialog(null, exception.getMessage());
			}
		});
	}
}