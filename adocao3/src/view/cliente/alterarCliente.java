package view.cliente;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Image;

import banco.clienteBanco;
import model.Cliente;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;

public class alterarCliente {

    protected Shell shell;
    private Text textCpfCliente;
    private Text textNomeCliente;
    private Text textTelCliente;
    private Text textEnderecoCliente;
    private clienteBanco clienteBanco;
    private Label lblDigiteOCpf;
    private Label lblInsiraOsNovos;
    private Label lblNomeDoCliente;
    private Label lblTelefone;
    private Label lblEndereo;
    private Label lblAlterarOsDados;
    private LocalResourceManager localResourceManager;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            alterarCliente window = new alterarCliente();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public alterarCliente() {
        clienteBanco = new clienteBanco();
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
        shell.setText("Alterar Cliente");

        // Carregar a imagem de fundo
        Image backgroundImage = new Image(Display.getCurrent(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsCl.jpeg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        textCpfCliente = new Text(shell, SWT.BORDER);
        textCpfCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textCpfCliente.setBounds(287, 104, 130, 18);

        textNomeCliente = new Text(shell, SWT.BORDER);
        textNomeCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textNomeCliente.setBounds(176, 199, 241, 18);

        textTelCliente = new Text(shell, SWT.BORDER);
        textTelCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textTelCliente.setBounds(176, 224, 241, 18);

        textEnderecoCliente = new Text(shell, SWT.BORDER);
        textEnderecoCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textEnderecoCliente.setBounds(176, 248, 241, 18);

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(45, 306, 97, 34);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));

        Button btnOK = new Button(shell, SWT.NONE);
        btnOK.setText("OK");
        btnOK.setBounds(394, 306, 97, 34);
        btnOK.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnOK.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        
        lblDigiteOCpf = new Label(shell, SWT.NONE);
        lblDigiteOCpf.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblDigiteOCpf.setBounds(63, 107, 218, 15);
        lblDigiteOCpf.setText("Digite o CPF do cliente que deseja alterar:");
        
        lblInsiraOsNovos = new Label(shell, SWT.NONE);
        lblInsiraOsNovos.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblInsiraOsNovos.setBounds(115, 158, 116, 15);
        lblInsiraOsNovos.setText("Insira os novos dados:");
        
        lblNomeDoCliente = new Label(shell, SWT.NONE);
        lblNomeDoCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblNomeDoCliente.setBounds(80, 202, 90, 15);
        lblNomeDoCliente.setText("Nome do Cliente:");
        
        lblTelefone = new Label(shell, SWT.NONE);
        lblTelefone.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblTelefone.setBounds(115, 227, 47, 15);
        lblTelefone.setText("Telefone:");
        
        lblEndereo = new Label(shell, SWT.NONE);
        lblEndereo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblEndereo.setBounds(115, 251, 55, 15);
        lblEndereo.setText("Endereço:");
        
        lblAlterarOsDados = new Label(shell, SWT.NONE);
        lblAlterarOsDados.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblAlterarOsDados.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblAlterarOsDados.setBounds(157, 22, 246, 24);
        lblAlterarOsDados.setText("Alterar os Dados do Cliente");

        btnOK.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Cliente cliente = new Cliente();
                cliente.setCpfCliente(textCpfCliente.getText());
                cliente.setNomeCliente(textNomeCliente.getText());
                cliente.setTelCliente(textTelCliente.getText());
                cliente.setEnderecoCliente(textEnderecoCliente.getText());

                clienteBanco.alterarCliente(cliente);

                MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                messagebox.setMessage("Cliente alterado com sucesso");
                messagebox.open();
            }
        });

        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                textNomeCliente.setText("");
                textTelCliente.setText("");
                textEnderecoCliente.setText("");
                shell.dispose(); // Fecha a janela ao cancelar
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(),shell);
    }
}
