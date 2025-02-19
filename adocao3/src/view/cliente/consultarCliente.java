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
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.FontDescriptor;

public class consultarCliente {
    protected Shell shell;
    private Text textCpfCliente;
    private Text textResultado;
    private clienteBanco clienteBanco;
    private LocalResourceManager localResourceManager;
    private Label lblResultado;
    private Label lblDigiteOCpf;
    private Label lblConsultarCliente;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            consultarCliente window = new consultarCliente();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public consultarCliente() {
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
        shell.setText("Consultar Cliente");

        // Carregar a imagem de fundo
        Image backgroundImage = new Image(Display.getCurrent(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsCl.jpeg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        textCpfCliente = new Text(shell, SWT.BORDER);
        textCpfCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textCpfCliente.setBounds(260, 74, 130, 21);

        textResultado = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        textResultado.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textResultado.setBounds(65, 164, 410, 120);

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnConsultar.setBounds(397, 302, 97, 34);
        btnConsultar.setText("Consultar");

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(43, 302, 97, 34);
        btnCancelar.setText("Cancelar");
        
        lblResultado = new Label(shell, SWT.NONE);
        lblResultado.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblResultado.setBounds(43, 140, 55, 15);
        lblResultado.setText("Resultado:");
        
        lblDigiteOCpf = new Label(shell, SWT.NONE);
        lblDigiteOCpf.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblDigiteOCpf.setBounds(43, 77, 201, 15);
        lblDigiteOCpf.setText("Digite o CPF do cliente para consultar:");
        
        lblConsultarCliente = new Label(shell, SWT.NONE);
        lblConsultarCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblConsultarCliente.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblConsultarCliente.setBounds(170, 10, 156, 21);
        lblConsultarCliente.setText("Consultar Cliente");

        // Evento para o botão "Consultar"
        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    String cpfCliente = textCpfCliente.getText();

                    // Criar o objeto Cliente para realizar a consulta
                    Cliente cliente = new Cliente();
                    cliente.setCpfCliente(cpfCliente);

                    // Chamar o método consultarCliente do clienteBanco
                    Cliente resultado = clienteBanco.consultarCliente(cliente);

                    // Exibir os dados retornados na interface
                    if (resultado != null) {
                        textResultado.setText("CPF: " + resultado.getCpfCliente() + "\n" +
                                              "Nome: " + resultado.getNomeCliente() + "\n" +
                                              "Telefone: " + resultado.getTelCliente() + "\n" +
                                              "Endereço: " + resultado.getEnderecoCliente() + "\n" +
                                              "Status: " + resultado.getStatus().name());
                    } else {
                        textResultado.setText("Cliente não encontrado.");
                    }
                } catch (Exception ex) {
                    textResultado.setText("Erro ao consultar o cliente: " + ex.getMessage());
                }
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos de texto
                textCpfCliente.setText("");
                textResultado.setText("");

                // Se necessário, pode fechar a janela ao cancelar
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
