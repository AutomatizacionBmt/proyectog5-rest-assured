Feature: Pruebas al api de proyectos de redmine
  Como usuario de redmine
  Yo quiero crear proyectos a través del api de proyectos
  para poder llevar un control de los proyectos

  @rest
  Scenario: Create un proyecto
    Given El sistema esta listo para recibir peticiones
    When El usuario envia una peticion al servicio de crear un proyecto
      | name            | RedmineProject              |
      | identifier      | redmineProject              |
      | description     | Redmine Project on cucumber |
      | inherit_members | false                       |
      | is_public       | true                        |
    Then El codigo de estado de la respuesta del servicio debe ser 201