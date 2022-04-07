# Properties Intepreter

## Introducción
Biblioteca para la interpretación de directivas y comandos
### Prioridad
1. Comandos - (FORMAT)
2. Evalua Propiedades
3. Evalua Expresiones
4. Directivas (IF)

#### Ejemplo 1:

Input:

Semana **\$NI(property1)** del **#FORMAT(\$DT, property2, DD/MM/YYYY)** al **#FORMAT(\$DT, property3, DD/MM/YYYY)**

Output:

Semana 12 del 01/02/2022 al 06/02/2022

#### Ejemplo 2:

Input:

#IF(\$N(property1) > \$N(property2)){FFFFFF}:{000000}

Output:

FFFFFF or 000000


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
| $NI | Integer |
| $NF | Float |
| $D | Date |
| $DT | DateTime |
| $T | DateTime |
| $B | Boolean |
| $A | Array |

#### Operadores
- Los operadores se dividen en aritméticos, comparación y lógicos

| Operador | Tipo | Descripción |
|--|--|--|
| + |Aritmético| **Suma Numeric y concatena String** |
| - |Aritmético| **Resta Numeric y concatena String** |
| * |Aritmético| **Multiplica Numeric** |
| / |Aritmético| **Divide Numeric** |
| ^ |Aritmético| **Exponential Operation** |
| == |Relacional| Igual a |
| != |Relacional| No es igual |
| > |Relacional| Mayor que |
| < |Relacional| Menor que |
| <= |Relacional| Menor o igual que |
| >= |Relacional| Mayor o igual a |
| && |Lógicos| **And lógico** |
| \|\||Lógicos | **Or lógico** |

#### Directivas
- Las directivas comienzan con # seguido del nombre.

| Directiva | Descripción |
|--|--|
| #IF | #IF(){}:{} |

#### Comandos
| Comandos| Descripción |
|--|--|
| #FORMAT | #FORMAT(tipo,propiedad,'formato') |
| #NOT| #NOT(boolean_expression) |
| #EQUAL| #EQUAL(boolean_expression) |


> Written with [StackEdit](https://stackedit.io/).