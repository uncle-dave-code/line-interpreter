# Bbr Properties Intepreter

## Introducción
Biblioteca para la interpretación de directivas y comandos en los valores de las propiedades del Bbr Customizer

## Definiciones
Las directivas deben estar encerradas dentro de "{}" y no pueden haber directivas dentro de otras.

Ejemplo:

Input: property1-> LocalDate

"Acá tenemos {1+1} directivas {Format($D,property1,#DD/MM/YYYY)}"

Output: Acá tenemos 2 directivas 11/12/2022



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
| + | Suma Numeric |
| - | Resta Numeric |
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



> Written with [StackEdit](https://stackedit.io/).