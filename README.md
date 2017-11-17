# Sistema de Gerenciamento de Service Desk - SGSD
### Uma Solução Web para o gerenciamento de uma Central de Serviços de TI.
### Desenvolvido por: Diego Hartwig - 2017 - Curitiba - PR - Brasil
## hartwig.diego@gmail.com

### Tecnologias: 
* Java EE
* JPA/Hibernate 
* JavaServer Faces
* PrimeFaces

Trata-se do desenvolvimento de uma solução web para o gerenciamento de uma Central de Serviços de TI, proporcionando maior controle na gestão das equipes técnicas e facilitando o feedback dos usuários. 
	
Após a abertura do chamado, um técnico irá assumir a tarefa ou a mesma poderá ser designada por um gestor, durante o atendimento poderão ser incluídas informações complementares de acompanhamento. Após a conclusão do atendimento o chamado poderá ser finalizado pelo técnico e então o sistema irá enviar automaticamente uma pesquisa de satisfação ao usuário.

Os gestores poderão acompanhar os chamados em aberto, em andamento e encerrados, bem como a quantidade de chamados encerradas por cada técnico ou equipe de TI. O Sistema irá permitir a emissão de relatórios de indicadores que poderão ser enviados periodicamente aos gestores e apresentados aos clientes.

Segue abaixo a descrição dos principais módulos do projeto SGSD:

* Módulos de cadastro: Os usuários autenticados no sistema com a permissão de administrador, poderão visualizar todos os chamados, bem como terão privilégios para cadastrar, editar e excluir usuários, técnicos, setores e equipes do sistema. Este módulo possui os seguintes formulários de cadastro: Usuários, Técnicos, Setores, Equipes e Chamados.

* Módulo de Listagem: Neste módulo será possível visualizar no formato de tabelas de dados as seguintes listagens: Listagens de Usuários, Técnicos, Setores, Equipes e Chamados.

* Módulo de Pesquisa: Irá permitir a visualização de consultas a partir da escolha de filtros de pesquisa e irá possibilitar a exportação dos itens selecionados para uma planilha do Excel.

Telas do SGSD

![login](https://user-images.githubusercontent.com/14913257/32973287-c1bcdec4-cbdd-11e7-984c-14415ff723ab.png)

Login

![chamados](https://user-images.githubusercontent.com/14913257/32973382-414b943c-cbde-11e7-91f7-bb041cac49b5.png)

Gerenciamento de Chamados

![tecnicos](https://user-images.githubusercontent.com/14913257/32973446-abbf4caa-cbde-11e7-8c81-26eab2c58537.png)

Técnicos

![usuarios](https://user-images.githubusercontent.com/14913257/32973481-de715986-cbde-11e7-8a77-7e3aff91d2a3.png)

Usuários

![novo_chamado](https://user-images.githubusercontent.com/14913257/32973508-05516e92-cbdf-11e7-9daa-cc0e9e674346.png)

Novo Chamado

![chamado_andamento](https://user-images.githubusercontent.com/14913257/32973706-4b074f00-cbe0-11e7-8cd5-4182a54d3c94.png)

Chamado em andamento

![chamado_encerrado](https://user-images.githubusercontent.com/14913257/32973735-741426fc-cbe0-11e7-9d39-89dbcf4baadd.png)

Chamado Encerrado

![configuracoes](https://user-images.githubusercontent.com/14913257/32973555-3e579d4c-cbdf-11e7-83e0-0ee8cc9a0147.png)

Configurações

![setores](https://user-images.githubusercontent.com/14913257/32973582-66c3cba2-cbdf-11e7-888a-8c2bbc4b3fe9.png)

Setores

![equipes](https://user-images.githubusercontent.com/14913257/32973620-9bc1f50e-cbdf-11e7-873c-a9232ab40eb8.png)

Equipes

![formulario_usuario](https://user-images.githubusercontent.com/14913257/32973642-c5ca4450-cbdf-11e7-8e2f-36def1946152.png)

Formulário de Pesquisa de Satisfação - Usuário

![avaliacao](https://user-images.githubusercontent.com/14913257/32973653-e8da5ff2-cbdf-11e7-9f33-963dbc1914ca.png)

Resultados das Pesquisas - Administrador

![filtro_pesquisa](https://user-images.githubusercontent.com/14913257/32973680-1878fcbe-cbe0-11e7-8e8d-550bd4e45596.png)

Filtros de pesquisa













