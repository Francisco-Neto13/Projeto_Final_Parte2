// Importações omitidas por brevidade

public class FXMLAnchorPaneCadastrosClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteNome;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteCPF;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Label labelClienteCodigo;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteCPF;
    @FXML
    private Label labelClienteTelefone;
    @FXML
    private TextField textFieldClienteTelefone;

    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        carregarTableViewClientes();
        selecionarItemTableViewClientes(null);
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
                (observavble, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    }

    public void carregarTableViewClientes(){
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listClientes = clienteDAO.listar();

        observableListClientes = FXCollections.observableArrayList(listClientes);
        tableViewClientes.setItems(observableListClientes);
    }

    public void selecionarItemTableViewClientes(Cliente cliente){
        if (cliente != null){
            labelClienteCodigo.setText(String.valueOf(cliente.getCdCliente()));
            labelClienteNome.setText(cliente.getNome());
            labelClienteCPF.setText(cliente.getCpf());
            labelClienteTelefone.setText(cliente.getTelefone());
        }else{
            labelClienteCodigo.setText("");
            labelClienteNome.setText("");
            labelClienteCPF.setText("");
            labelClienteTelefone.setText("");
        }
    }


    @FXML
    public void handleButtonInserir() throws IOException {
        Cliente cliente = new Cliente();
        cliente.setTelefone(textFieldClienteTelefone.getText()); // Adiciona o telefone do cliente
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
        if (buttonConfirmarClicked){
            clienteDAO.inserir(cliente);
            carregarTableViewClientes();
        }
    }


    @FXML
    public void handleButtonAlterar() throws IOException {
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            cliente.setTelefone(textFieldClienteTelefone.getText()); // Atualiza o telefone do cliente
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
            if (buttonConfirmarClicked) {
                clienteDAO.alterar(cliente);
                carregarTableViewClientes();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            clienteDAO.remover(cliente);
            carregarTableViewClientes();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na tabela!");
            alert.show();
        }
    }

    public boolean showFXMLAnchorPaneCadastrosClientesDialog(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastrosClientesDialogController.class.getResource("/javafxmvc/view/FXMLAnchorPaneCadastrosClientesDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Clientes");

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLAnchorPaneCadastrosClientesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);

        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
    }
}
