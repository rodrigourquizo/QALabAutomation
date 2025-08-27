Feature: Creacion y consulta de compra de mascota

  # Se considera ids entre 1 y 10 como se indica en la documentacion de la API
  # For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions
  @TestEjecucion
  Scenario Outline: Creacion de orden
    Given defino la URL "https://petstore.swagger.io/v2/"
    When registro la orden con ID "<orderId>", petId "<petId>", cantidad "<quantity>", status "<status>", complete "<complete>"
    Then valido el status code del response igual a <StatusCodeEspeerado>
    And valido el body de la orden sea "<orderId>", "<petId>", "<quantity>", "<status>", "<complete>"

    Examples:
      | orderId | petId | quantity | status  | complete | StatusCodeEspeerado |
      | 1 | 10 | 2 | placed  | true | 200 |
      | 2 | 10 | 2 | placed  | true | 200 |
      | 3 | 10 | 2 | placed  | true | 200 |

  @TestEjecucion
  Scenario Outline: Consulta de orden
    Given defino la URL "https://petstore.swagger.io/v2/"
    When consulto la orden de ID "<orderId>"
    Then valido el status code del response igual a <StatusCodeEspeerado>
    And valido el body de la orden sea "<orderId>", "<petId>", "<quantity>", "<status>", "<complete>"

    Examples:
      | orderId | petId | quantity | status | complete | StatusCodeEspeerado |
      | 1 | 10 | 2  | placed | true | 200 |
      | 2 | 10 | 2 | placed  | true | 200 |
      | 3 | 10 | 2 | placed  | true | 200 |



