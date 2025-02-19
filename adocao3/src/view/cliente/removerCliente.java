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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.FontDescriptor;

public class removerCliente {
    protected Shell shell;
    private Text textCpfCliente;
    private clienteBanco clienteBanco;
    private LocalResourceManager localResourceManager;
    private Label lblDigiteOCpf;
    private Label lblRemoverCliente;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            removerCliente window = new removerCliente();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public removerCliente() {
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
        shell.setText("Remover Cliente");

        // Carregar a imagem de fundo
        Image backgroundImage = new Image(Display.getCurrent(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsCl.jpeg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        textCpfCliente = new Text(shell, SWT.BORDER);
        textCpfCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textCpfCliente.setBounds(287, 159, 120, 21);

        Button btnRemover = new Button(shell, SWT.NONE);
        btnRemover.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnRemover.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnRemover.setBounds(391, 303, 97, 34);
        btnRemover.setText("Remover");

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(37, 303, 97, 34);
        btnCancelar.setText("Cancelar");

        lblDigiteOCpf = new Label(shell, SWT.NONE);
        lblDigiteOCpf.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblDigiteOCpf.setBounds(72, 162, 196, 15);
        lblDigiteOCpf.setText("Digite o CPF do cliente para remover:");

        lblRemoverCliente = new Label(shell, SWT.NONE);
        lblRemoverCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblRemoverCliente.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblRemoverCliente.setBounds(186, 40, 150, 24);
        lblRemoverCliente.setText("Remover Cliente");

        // Evento para o botão "Remover"
        btnRemover.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    String cpfCliente = textCpfCliente.getText();

                    // Validação básica do CPF
                    if (cpfCliente == null || cpfCliente.isEmpty()) {
                        throw new IllegalArgumentException("CPF não pode ser vazio.");
                    }

                    // Criar o objeto Cliente
                    Cliente cliente = new Cliente();
                    cliente.setCpfCliente(cpfCliente);

                    // Chama o método de removerCliente que invoca o procedimento no banco
                    clienteBanco.removerCliente(cliente);

                    // Exibe MessageBox de sucesso
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                    messagebox.setMessage("Cliente com CPF " + cpfCliente + " removido com sucesso.");
                    messagebox.open();
                } catch (IllegalArgumentException ex) {
                    // Exibe MessageBox de erro se o CPF for inválido
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro: " + ex.getMessage());
                    messagebox.open();
                } catch (Exception ex) {
                    // Exibe MessageBox de erro em caso de falha no processo de remoção
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro ao remover o cliente: " + ex.getMessage());
                    messagebox.open();
                }
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos de texto
                textCpfCliente.setText("");

                // Se necessário, pode fechar a janela ao cancelar
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
