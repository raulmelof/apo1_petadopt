package view.cliente;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.clienteBanco;
import model.Cliente;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Image;

public class inserirCliente {
    protected Shell shell;
    private Text txtCpfCliente;
    private Text txtNomeCliente;
    private Text txtEnderecoCliente;
    private Text txtTelCliente;
    private clienteBanco clienteBanco;
    private LocalResourceManager localResourceManager;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            inserirCliente window = new inserirCliente();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public inserirCliente() {
        clienteBanco = new clienteBanco(); // Inicializa o objeto para interagir com o banco
    }

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        createResourceManager();
        shell.setSize(550, 400);
        shell.setText("Inserir Cliente");

        // Carregar a imagem de fundo
        Image backgroundImage = new Image(Display.getCurrent(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsCl.jpeg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        // Labels para os campos
        Text lblCpfCliente = new Text(shell, SWT.NONE);
        lblCpfCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblCpfCliente.setText("Insira o CPF:");
        lblCpfCliente.setBounds(96, 112, 71, 15);
        lblCpfCliente.setEditable(false);

        Text lblNomeCliente = new Text(shell, SWT.NONE);
        lblNomeCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblNomeCliente.setText("Insira o Nome:");
        lblNomeCliente.setBounds(84, 152, 83, 15);
        lblNomeCliente.setEditable(false);

        Text lblEnderecoCliente = new Text(shell, SWT.NONE);
        lblEnderecoCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblEnderecoCliente.setText("Insira o Endereço:");
        lblEnderecoCliente.setBounds(70, 192, 97, 15);
        lblEnderecoCliente.setEditable(false);

        Text lblTelCliente = new Text(shell, SWT.NONE);
        lblTelCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblTelCliente.setText("Insira o Telefone:");
        lblTelCliente.setBounds(77, 232, 94, 15);
        lblTelCliente.setEditable(false);

        // Campos de texto para entrada de dados
        txtCpfCliente = new Text(shell, SWT.BORDER);
        txtCpfCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        txtCpfCliente.setBounds(177, 109, 250, 30);

        txtNomeCliente = new Text(shell, SWT.BORDER);
        txtNomeCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        txtNomeCliente.setBounds(177, 149, 250, 30);

        txtEnderecoCliente = new Text(shell, SWT.BORDER);
        txtEnderecoCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        txtEnderecoCliente.setBounds(177, 189, 250, 30);

        txtTelCliente = new Text(shell, SWT.BORDER);
        txtTelCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        txtTelCliente.setBounds(177, 229, 250, 30);

        // Botão para salvar
        Button btnSalvar = new Button(shell, SWT.NONE);
        btnSalvar.setText("Salvar");
        btnSalvar.setBounds(410, 294, 97, 34);
        btnSalvar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnSalvar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));

        // Botão para cancelar
        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(36, 294, 97, 34);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        
        Label lblCadastrarCliente = new Label(shell, SWT.NONE);
        lblCadastrarCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblCadastrarCliente.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblCadastrarCliente.setBounds(196, 24, 154, 24);
        lblCadastrarCliente.setText("Cadastrar Cliente");

        btnSalvar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String cpfString = txtCpfCliente.getText().trim(); // Pega o CPF como string

                // Validação: Verifica se o CPF tem exatamente 11 dígitos e é numérico
                if (cpfString.length() != 11 || !cpfString.matches("\\d+")) {
                    System.out.println("Erro: CPF deve ter 11 dígitos numéricos.");
                    return;  // Retorna ou exibe a mensagem de erro
                }

                // Cria o objeto cliente
                Cliente cliente = new Cliente();
                cliente.setCpfCliente(cpfString);
                cliente.setNomeCliente(txtNomeCliente.getText());
                cliente.setEnderecoCliente(txtEnderecoCliente.getText());
                cliente.setTelCliente(txtTelCliente.getText());

                // Chama o método para inserir o cliente no banco
                clienteBanco.inserirCliente(cliente);

                // Limpa os campos de texto após inserir
                txtCpfCliente.setText("");
                txtNomeCliente.setText("");
                txtEnderecoCliente.setText("");
                txtTelCliente.setText("");
            }
        });

        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos de texto
                txtCpfCliente.setText("");
                txtNomeCliente.setText("");
                txtEnderecoCliente.setText("");
                txtTelCliente.setText("");
                shell.dispose(); // Fecha a janela
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
