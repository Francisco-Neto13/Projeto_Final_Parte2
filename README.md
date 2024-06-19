# Projeto_Final_Parte2
Projeto Final, parte 2. Unima 2024 - Projeto de Programação

Foram feitas algumas alterações para adicionar um campo de texto para o nome da categoria na interface gráfica do aplicativo JavaFX.

Inicialmente, um novo campo TextField foi adicionado ao controlador para representar o campo de entrada do nome da categoria. Esse campo foi então associado ao respectivo elemento na interface gráfica por meio da anotação @FXML.

Em seguida, durante a inicialização do controlador, foi configurado o método initialize para limpar o campo de texto do nome da categoria ao carregar a tabela de categorias. Isso garante que o campo esteja vazio quando nenhum item da tabela estiver selecionado.

Além disso, a exibição do nome da categoria na tabela foi configurada para ser feita na coluna correspondente, utilizando o método setCellValueFactory para vincular os dados da tabela à propriedade descricao da classe Categoria.

Por fim, nos métodos responsáveis por inserir e alterar categorias, foi adicionado o código para obter o texto do campo de texto do nome da categoria e atribuí-lo ao objeto Categoria antes de realizar a inserção ou a alteração no banco de dados. Isso garante que o nome da categoria seja atualizado de acordo com o que foi digitado pelo usuário na interface gráfica.

As alterações realizadas no código visam adicionar a funcionalidade de escolher a categoria do produto ao cadastrá-lo ou editá-lo. Primeiramente, um `ComboBox` foi adicionado à interface gráfica para exibir e selecionar a categoria do produto. Em seguida, no método `initialize`, a lista de categorias é carregada no `ComboBox` através do método `carregarComboBoxCategorias()`, garantindo que as opções estejam disponíveis ao iniciar o programa.

O método `selecionarItemTableViewProdutos` foi atualizado para exibir a categoria do produto selecionado no `ComboBox`. Quando um produto é selecionado na tabela, sua categoria correspondente é recuperada e usada para selecionar o item correspondente no `ComboBox`, garantindo que a categoria do produto seja exibida corretamente.

Os métodos `handleButtonInserir` e `handleButtonAlterar` foram modificados para atribuir a categoria selecionada no `ComboBox` ao produto que está sendo inserido ou alterado. Isso garante que a categoria escolhida seja associada corretamente ao produto.

Por fim, o método `showFXMLAnchorPaneCadastrosProdutosDialog` foi atualizado para passar a categoria selecionada no `ComboBox` para o diálogo de cadastro de produtos, permitindo que a categoria seja selecionada ou alterada ao cadastrar ou editar um produto. Essas alterações tornam o sistema mais completo e funcional, permitindo uma gestão mais precisa e organizada dos produtos com suas respectivas categorias.
