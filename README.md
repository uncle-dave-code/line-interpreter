# Bbr Properties Intepreter

## Introducción
Biblioteca para la interpretación de directivas y comandos en los valores de las propiedades del Bbr Customizer

## Definiciones

### Sintaxis
En el lenguaje, van existir:
- Palabras reservadas
- Caracteres especiales
- Operadores
- Directivas
- .........

### Palabras Reservadas

#### Tipos de Datos
- Los tipos de variables comienzan con $ seguido del tipo.

| Tipo | Descripción |
|--|--|
| $S | String |
| $N | Numeric |
| $D | Date |
| $DT | DateTime |
| $B | Boolean |
| $A | Array |

#### Operadores
- Los operadores se dividen en aritméticos, comparación y lógicos

| Operador | Descripción |
|--|--|
| + | Suma Numeric y concatena String |
| - | Resta Numeric y concatena String |
| * | Multiplica Numeric |
| / | Divide Numeric |
| == | Igual a |
| != | No es igual |
| > | Mayor que |
| < | Menor que |
| <= | Menor o igual que |
| >= | Mayor o igual a |
| & | And lógico |
| \| | Or lógico |

#### Directivas
- Las directivas comienzan con # seguido del nombre.

| Directiva | Descripción |
|--|--|
| #if | #if()[] |
| #else | #if()[] |
| #format | #format(tipo,'formato') |


> Written with [StackEdit](https://stackedit.io/).