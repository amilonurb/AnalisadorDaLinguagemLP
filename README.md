<h1>Analisador Léxico Sintático</h1>

<h2>Atividade Prática Supervisionada da disciplina Modelos de Linguagem de Programação</h2>

Tarefa: criação de um analisador léxico e sintático para a linguagem de exemplo especificada na aula sobre BNF

Detalhes:
<ul>
    <li>A classes-parsers foram feitas usando a ferramenta <b>JavaCC</b>, um <i>compiler-compiler</j> feito em Java</li>
    <li>Ele gera as classes baseado na gramática e nas regras de produção especificadas no arquivo com e extensão <b>.jj</b></li>
    <li>O arquivo com o código deve estar com e extensão <b>.lp</b></li>
</ul>

BNF
REGRAS LÉXICAS:
<OP>-> '+' | '-' | '*' | '/' | '=' | '!=' | '>' | '<' | '>=' | '<='
<ID>-> <LETTER> <LD>*
<LD>-> <LETTER> | <DIGIT>
<NUMBER>-> <DIGIT>+
<LETTER>-> 'a' | 'b' | ... | 'z'
<DIGIT>-> '0' | '1' | ... | '9'

REGRAS SINTÁTICAS:
<PROGRAM>-> 'program' '{' <STATEMENT>* '}'
<STATEMENT>-> <ASSIGNMENT> | <CONDITIONAL> | <LOOP>
<ASSIGNMENT>-> <ID> '=' <EXPR> ';'
<CONDITIONAL>-> 'if' <EXPR> '{' <STATEMENT>+ '}' | 
        		'if' <EXPR> '{' <STATEMENT>+ '}' 'else' '{' <STATEMENT>+ '}'
<LOOP>-> 'while' <EXPR> '{' <STATEMENT>+ '}'
<EXPR>-> <ID> | <NUMBER> | '(' <EXPR> ')' | <EXPR> <OP> <EXPR>

