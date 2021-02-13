Feature: Pruebas al api de issues de redmine
  Como usuario de redmine
  Yo quiero crear issues a través del api de issues
  para poder llevar un control de los issues

  @rest
  Scenario: Obtener el listado de todos los issues
    Given El sistema esta listo para recibir peticiones
    When Un usuario envia una peticion al servicio de listar todos los issues
    Then El codigo de estado de la respuesta del servicio debe ser 200


  @rest
  Scenario: Obtener un issue por su id
    Given El sistema esta listo para recibir peticiones
    When Un usuario envia una peticion al servicio de obtener un issue por su id
      | id | 3487 |
    Then El codigo de estado de la respuesta del servicio debe ser 200
    And El sistema debe responder con la siguiente data:
      | id          | 3487                                 |
      | subject     | Issue modificado por JHHA            |
      | description | Esta es una descripcion paara prueba |
      | start_date  | 2021-02-12                           |











