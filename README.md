# Trabajo Práctico Integrador de Lenguajes y Compiladores

## Consideraciones Generales

Es necesario cumplir con las siguientes consideraciones para evaluar el TP:

1. Cada grupo deberá desarrollar el compilador teniendo en cuenta:
   - Todos los temas comunes.
   - Los temas especiales asignados.
   - El método de generación intermedia asignado.
   - El profesor que le ha sido asignado.

Nota: Los grupos y sus respectivas asignaciones están listados en esta [planilla](https://docs.google.com/spreadsheets/d/1q0wSSuOOZrq44yPZkvd-_2GVTXgiVOFYKHgxdbQgkDk/edit#gid=0).

2. Cada grupo deberá designar un integrante para el envío de los correos durante todo el cuatrimestre.
3. Todos los ejecutables deberán correr en la JVM 18.
4. El TP deberá respetar la estructura provista en esta planilla y ser entregado a través de un enlace al repositorio de GitHub generado.
5. Para generar su propio repositorio GitHub seleccione la opción "Use this template".
6. Se fijan a continuación puntos de control con fechas y requerimientos determinados.


### Primera Entrega:

**Objetivo**: Realizar un analizador sintáctico utilizando las herramientas JFLEX y CUP. 
El programa ejecutable deberá mostrar por pantalla las reglas sintácticas que va analizando el parser en base a un archivo de entrada (test.txt). 
Las impresiones deben ser claras. Las reglas que no realizan ninguna acción no deben generar salida.

La entrega 1.0.0 incluirá:

- El archivo lexer.flex con la definición de todos los componentes léxicos.
- El archivo parser.cup con la definición de la gramática del lenguaje y la lógica de generación de la tabla de símbolos.
- El archivo symbol-table.txt deberá contener la lista de variables y constantes con sus respectivos atributos.
- El archivo ejecutable lyc-compiler-1.0.0.jar.
- El archivo de pruebas test.txt que contendrá ejemplos de todos los temas comunes y especiales (selecciones, ciclos anidados, temas especiales, verificación de cotas para las constantes, chequeo de longitud de los nombres de los identificadores, comentarios, etc).
  - Dicho archivo debe ser único (no enviar diferentes escenarios de prueba en diferentes archivos).
  - Las líneas de código que ejemplifican casos de error en tiempo de compilación deberán presentarse en el documento de manera comentadas y acompañadas de un mensaje descriptivo.

Nota: Los archivos requeridos ya son provistos/generados por la plantilla, lo que debe agregarse es la implementación de la funcionalidad en ellos.

##### Criterio de aprobación:
- Todos los casos de prueba escritos en LexerTest y ParserTest deberán pasar.
- La tabla de símbolos debe generarse respetando la estructura descrita en la consigna.

Envíe el enlace del repositorio generado enviado a: lenguajesycompiladores@gmail.com

Asunto: NombredelDocente_GrupoXX (Ej Daniel_Grupo03, Eleazar_Grupo02, Facundo_Grupo15)

Fecha de entrega: 19/09/2022


### Segunda Entrega:

**Objetivo:** Realizar un generador de código intermedio utilizando el archivo parser.cup generado en la primera entrega. El programa ejecutable deberá procesar el archivo de entrada (prueba.txt) y devolver el código intermedio del mismo junto con la tabla de símbolos.

Se deberá entregar una carpeta con nombre: GrupoXX que incluirá:

- El archivo lexer.flex con la definición de todos los componentes léxicos.
- El archivo parser.cup con la definición de la gramática del lenguaje y la lógica de generación de la tabla de símbolos.
- El archivo symbol-table.txt deberá contener la lista de variables y constantes con sus respectivos atributos.
- El archivo intermediate-code.txt y que contiene el código intermedio generado.
- El archivo ejecutable lyc-compiler-2.0.0.jar.
- El archivo de pruebas test.txt que contendrá ejemplos de todos los temas comunes y especiales (selecciones, ciclos anidados, temas especiales, verificación de cotas para las constantes, chequeo de longitud de los nombres de los identificadores, comentarios, etc).
    - Dicho archivo debe ser único (no enviar diferentes escenarios de prueba en diferentes archivos).
    - Las líneas de código que ejemplifican casos de error en tiempo de compilación deberán presentarse en el documento de manera comentadas y acompañadas de un mensaje descriptivo.

Nota: Los archivos requeridos ya son provistos/generados por la plantilla, lo que debe agregarse es la implementación de la funcionalidad en ellos.

##### Criterio de aprobación:
- El código intermedio debe generarse correctamente.
- Deben agregarse validaciones semánticas (Por ej: validación de tipos en asignación, variable ya declarada, etc.)


Envíe el enlace del repositorio generado enviado a: lenguajesycompiladores@gmail.com

Asunto: NombredelDocente_GrupoXX    (Ej Daniel_Grupo03, Eleazar_Grupo02)

Fecha de entrega: 24/10/2022

### Entrega final

***Objetivo***: Realizar un compilador utilizando el archivo generado en la segunda entrega. 
El programa ejecutable deberá procesar el archivo de entrada (test.txt), compilarlo y ejecutarlo.

Se deberá entregar una carpeta con nombre: GrupoXX que incluirá:

- El archivo lexer.flex con la definición de todos los componentes léxicos.
- El archivo parser.cup con la definición de la gramática del lenguaje y la lógica de generación de la tabla de símbolos.
- El archivo symbol-table.txt deberá contener la lista de variables y constantes con sus respectivos atributos.
- El archivo intermediate-code.txt y que contiene el código intermedio generado.
- El archivo ejecutable lyc-compiler-3.0.0.jar.
- El archivo de pruebas test.txt que contendrá ejemplos de todos los temas comunes y especiales (selecciones, ciclos anidados, temas especiales, verificación de cotas para las constantes, chequeo de longitud de los nombres de los identificadores, comentarios, etc).
    - Dicho archivo debe ser único (no enviar diferentes escenarios de prueba en diferentes archivos).
    - Las líneas de código que ejemplifican casos de error en tiempo de compilación deberán presentarse en el documento de manera comentadas y acompañadas de un mensaje descriptivo.
- El archivo assembler que se llamará final.asm
- El archivo por lotes run.bat que incluirá las sentencias necesarias para compilar con TASM y TLINK el archivo final.asm generado por el compilador

Nota: Los archivos requeridos ya son provistos/generados por la plantilla, lo que debe agregarse es la implementación de la funcionalidad en ellos.

##### Criterio de aprobación:
- El código assembler debe generarse correctamente.
- El programa de prueba debe ejecutarse sin problemas en DOSBox.

Envíe el enlace del repositorio generado enviado a: lenguajesycompiladores@gmail.com

Asunto: NombredelDocente_GrupoXX    (Ej Daniel_Grupo03, Eleazar_Grupo02)

Fecha de entrega: 07/11/2022

## Temas comunes

### While

[Ejemplo](src/test/resources/while.txt)

### If

[Ejemplo](src/test/resources/if.txt)

### Asignaciones

[Ejemplo](src/test/resources/assignments.txt)

### Tipos de datos

- Float (32 bits): el separador decimal será el punto “.”
- Int (16 bits)
- String: constantes de 40 caracteres alfanuméricos como máximo, limitada por comillas (“ “) ,de la forma “XXXX”

[Ejemplo](src/test/resources/assignments.txt)

### Declaración de variables
Todas las variables deberán ser declaradas dentro de un bloque especial para ese fin,
delimitado por la palabra reservada init dentro de un bloque delimitado por llaves.

[Ejemplo](src/test/resources/init.txt)

Cada línea dentro del bloque tendrá la forma: < Lista de Variables> :  Tipo de Dato
La Lista de Variables debe ser una lista de variables separadas por comas.
Pueden existir varias líneas de declaración de tipos, incluso utilizando más de una línea para el mismo tipo.

IMPORTANTE: Las variables no guardan su valor en tabla de símbolos.
Las asignaciones deben ser permitidas, solo en los casos en los que los tipos son compatibles, caso contrario deberá desplegarse un error.

### Comentarios
Deberán estar delimitados por `/*` y `*/` y podrán estar anidados en un solo nivel.

[Ejemplo Comentario](src/test/resources/comment.txt)

Nota: Los comentarios se ignoran, de manera que no generan un componente léxico o token

### Entrada y salida
Las salidas y entradas por teclado se implementarán como se muestra en el siguiente ejemplo:

[Ejemplo Entrada](src/test/resources/read.txt)

[Ejemplo Salida](src/test/resources/write.txt)


### Condiciones
Las condiciones para un constructor de ciclos o de selección pueden ser simples ( a < b )  o múltiples.
Las condiciones múltiples pueden ser hasta dos condiciones simples ligadas a través del operador lógico (AND, OR) o una condición simple con el operador lógico NOT

[Ejemplo AND](src/test/resources/and.txt)

[Ejemplo OR](src/test/resources/or.txt)

[Ejemplo NOT](src/test/resources/not.txt)


### Tabla de Símbolos
La tabla de símbolos tiene la capacidad de guardar las variables y constantes con sus atributos.
Los atributos portan información necesaria para operar con constantes, variables, etc.

![symbol-table.png](images/symbol-table.png)

## Temas especiales
 
### #Iguales
Esta función del lenguaje tomará como entrada una expresión (pivot)  y una lista de expresiones. Devolverá la cantidad de elementos iguales al pivot que se encuentran en la lista. La cantidad de listas es indefinida.

**Ejemplo:**

`#Iguales ( a+w/b, [(d-3)*2,e,f] )  = 2 si  (a+w/b = (d-3)*2 ) & (a+w/b =f) & (a+w/b ≠ e)`

### AllEqual
Esta función del lenguaje tomará como entrada dos a más listas de expresiones y devolverá verdadero si cada elemento en orden posicional es igual a su par correspondiente en cada   una de las listas. Caso contrario: Falso. La cantidad de listas es indefinida.

**Ejemplo:**

`AllEqual ( [a+w,b,c], [(d-3)*2,e,f], [g,h,i] )  es True si (a+w = (d-3)*2 = g) & (b = e = h) & (c=f=i)
`

### Case
Se deberá implementar la sentencia DO CASE con la última cláusula DEFAULT, que será opcional
Ejemplo:

```
DO a
CASE a=101
…
CASE a>202
…
DEFAULT ## opcional
ENDDO

```
### Repeat Inline

Esta función del lenguaje repetirá un conjunto de una o más sentencias [sentencias, la cantidad de veces indicada por la constante entera ctentera

`REPEAT ctentera [sentencias]
`

## Instrucciones para uso de la Plantilla JAVA

Para entender cómo usar esta plantilla, lea [estas instrucciones](Template-Instructions.MD).


## ¿Puedo usar otro lenguaje para el Trabajo Práctico?

Si bien la cátedra provee material y recursos para desarrollar el TP en JAVA, estamos abierto al uso de otras
tecnologías.

Probablemente, el uso de otras herramientas requerirá un trabajo de investigación adicional al grupo/equipo que así lo decida, y mayor
autonomía en las particularidades de implementación (dado que los docentes no conocemos todas las herramientas y no podremos dar soporte
a cada una de ellas).

De igual forma, el esfuerzo de investigación de otras herramientas será tenido en cuenta positivamente en la evaluación definitiva.

Dejamos a continuación, links útiles para implementar un compilador en otros lenguajes: 

[(Plantilla para TP Integrador usando Flex y Bison - C)](https://github.com/Lenguajes-y-Compiladores-UnLaM/compiler-c)



