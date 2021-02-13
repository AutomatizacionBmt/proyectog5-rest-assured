Feature: Pruebas al api de proyectos de redmine
  Como usuario de redmine
  Yo quiero crear proyectos a través del api de proyectos
  para poder llevar un control de los proyectos

  @rest
  Scenario: Create un proyecto
    Given El sistema esta listo para recibir peticiones
    When El usuario envia una peticion al servicio de crear un proyecto
      | name            | RedmineProject              |
      | identifier      | redmineproject              |
      | description     | Redmine Project on cucumber |
      | inherit_members | false                       |
      | is_public       | true                        |
    Then El codigo de estado de la respuesta del servicio debe ser 201


  @rest
  Scenario Outline: Create un proyecto con ejemplos
    Given El sistema esta listo para recibir peticiones
    When El usuario crea varios proyectos:
      | name   | identifier   | description   | inherit_members   | is_public   |
      | <name> | <identifier> | <description> | <inherit_members> | <is_public> |
    Then El codigo de estado de la respuesta del servicio debe ser 201

    Examples: Escenarios happy paths para creacion de proyecto
      | name            | identifier     | description  | inherit_members | is_public |
      | RedmineProject1 | redmineproject | description1 | false           | true      |
      | RedmineProject2 | redmineproject | description2 | false           | true      |
      | RedmineProject3 | redmineproject | description3 | false           | true      |
