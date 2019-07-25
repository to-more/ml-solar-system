# Enunciado 

Realizar un programa informático para poder predecir en los próximos 10 años:
1. ¿Cuántos períodos de sequía habrá?
2. ¿Cuántos períodos de lluvia habrá y qué día será el pico máximo de lluvia?
3. ¿Cuántos períodos de condiciones óptimas de presión y temperatura habrá?
Bonus:
Para poder utilizar el sistema como un servicio a las otras civilizaciones, los Vulcanos requieren
tener una base de datos con las condiciones meteorológicas de todos los días y brindar una API
REST de consulta sobre las condiciones de un día en particular.
1) Generar un modelo de datos con las condiciones de todos los días hasta 10 años en adelante
utilizando un job para calcularlas.
2) Generar una API REST la cual devuelve en formato JSON la condición climática del día
consultado.
3) Hostear el modelo de datos y la API REST en un cloud computing libre (como APP Engine o
Cloudfoudry) y enviar la URL para consulta:

# Solucion

La solucion queda planteada como sigue:

1. Se analizan las posiciones de los planetas emulando su movimiento en el tiempo. Esto implica rotar los planetas una vez por unidad de tiempo (en este caso la unidad es 1 día).
2. Para cada posicion se verifica que:
    - a. Los planetas esten alineados entre si y con el sol
        - Para verificar que los planetas esten alineados, se chequea que sus respectivos puntos sean equivalentes entre si, por ejemplo si se tiene **p1, p2, p3 entonces (p1 - p2) sea equivalente a (p2 - p3)** si además **p1 y p2 estan alineados con el sol** entonces cumple con el caso a) caso contrario cumple con el caso b).
    - b. Los planetas esten alineados entre si y no con el sol
    - c. Los planetas no esten alineados entre si y contengan al sol
        -  De no estar alineados deben formar un triangulo. Para chequear que contengan al sol, se calcula el area total del triangulo y de los sub triangulos formados entre el sol y los planetas, si se cumple que => 
          `area sol-p1 + area sol-p2 + area sol-p3 <= Area Total`
           Entonces contienen al sol. Si se verifica lo contrario se asume el caso d)    
           
    - d. Los planetas no esten alineados entre si y no contengan al sol
3. Adicionalmente se calcula el perimetro del triangulo formado por los planetas cuando no estan alineados, de esta forma se puede obtener el día de lluvia más intenso.
4. Luego de realizar el analisis en los puntos 1), 2) y 3) se puede obtener el resultado ejecutando por ejemplo 

    `GET $HOST/weathers/:dia => devuelve el clima para el dia especificado`
    
    `GET $HOST/predictions?startDay=1&endDay=100 => devuelve un reporte con la cantidad de dias de lluvia, sol, clima seco y optimo más el clima lluvioso más intenso.`


#### **El modelo se crea en el arranque de la aplicación o ejecutando el sgte endpoint:**

```bash 
curl -XPOST $HOST/predictions?startDay=1&endDay=100
```


## Rutas:

| Path        | Metodo          | Descripción      |
| ------------- |:-------------:| ---------:|
| /predictions/:id | GET | Retorna las predicciones para un rango de dias |
| /predictions/:id | POST | Genera las predicciones para un rango de dias |
| /weathers/:dia | GET | Devuelve el clima para el dia dado |
| /weathers/ | DELETE | Borra la base de climas |
| /planets/  | POST | Crean un planeta |
| /planets/  | GET | Lista los planetas |
| /planets/:name | GET | Retorna un planeta |
| /planets/ | DELETE | Borra los planetas |



## Local
### Build

Ejecuta el build de gradle + test + coverage y buildea una imagen de docker con la aplicacion boot

```bash
sh Build.sh
```

## Run

Ejecuta el container de docker com la aplicacion y una base mongo

```bash
sh Run.sh
```

# Stack tecnologico

* Java 8
* Spring boot
* Spock test
* Docker
* Mongo db
