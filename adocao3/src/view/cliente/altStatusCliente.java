package view.cliente;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.clienteBanco;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Image;

public class altStatusCliente {
    protected Shell shell;
    private Text textCpfCliente;
    private clienteBanco clienteBanco;
    private LocalResourceManager localResourceManager;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
        	altStatusCliente window = new altStatusCliente();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public altStatusCliente() {
        clienteBanco = new clienteBanco(); // Inicializa a classe responsável pela interação com o banco
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
        shell.setText("Gerenciar Cliente");

        // Carregar a imagem de fundo
        Image backgroundImage = new Image(Display.getCurrent(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsCl.jpeg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        // Label para CPF
        org.eclipse.swt.widgets.Label lblCpf = new org.eclipse.swt.widgets.Label(shell, SWT.NONE);
        lblCpf.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblCpf.setBounds(133, 107, 123, 15);
        lblCpf.setText("Digite o CPF do cliente:");

        // Campo de entrada para CPF
        textCpfCliente = new Text(shell, SWT.BORDER);
        textCpfCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textCpfCliente.setBounds(262, 104, 150, 25);

        // Botão Aprovar Cliente
        Button btnAprovar = new Button(shell, SWT.NONE);
        btnAprovar.setBounds(213, 171, 120, 30);
        btnAprovar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnAprovar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnAprovar.setText("Aprovar Cliente");

        btnAprovar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String cpfCliente = textCpfCliente.getText();
                try {
                    clienteBanco.aprovarCliente(cpfCliente);
                    showMessage("Cliente aprovado com sucesso!");
                } catch (Exception ex) {
                    showMessage("Erro ao aprovar cliente: " + ex.getMessage());
                }
            }
        });

        // Botão Recusar Cliente
        Button btnRecusar = new Button(shell, SWT.NONE);
        btnRecusar.setBounds(213, 207, 120, 30);
        btnRecusar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnRecusar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnRecusar.setText("Recusar Cliente");

        btnRecusar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String cpfCliente = textCpfCliente.getText();
                try {
                    clienteBanco.recusarCliente(cpfCliente);
                    showMessage("Cliente recusado com sucesso!");
                } catch (Exception ex) {
                    showMessage("Erro ao recusar cliente: " + ex.getMessage());
                }
            }
        });

        // Botão Adicionar à Lista Negra
        Button btnListaNegra = new Button(shell, SWT.NONE);
        btnListaNegra.setBounds(181, 243, 180, 30);
        btnListaNegra.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnListaNegra.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN));
        btnListaNegra.setText("Adicionar à Lista Negra");

        btnListaNegra.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String cpfCliente = textCpfCliente.getText();
                try {
                    clienteBanco.adcListaNegra(cpfCliente);
                    showMessage("Cliente adicionado à Lista Negra com sucesso!");
                } catch (Exception ex) {
                    showMessage("Erro ao adicionar cliente à Lista Negra: " + ex.getMessage());
                }
            }
        });

        // Botão para fechar a janela
        Button btnFechar = new Button(shell, SWT.NONE);
        btnFechar.setBounds(20, 299, 120, 30);
        btnFechar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnFechar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnFechar.setText("Fechar");
        
        Label lblStatusDoCliente = new Label(shell, SWT.NONE);
        lblStatusDoCliente.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblStatusDoCliente.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblStatusDoCliente.setBounds(181, 29, 154, 25);
        lblStatusDoCliente.setText("Status do Cliente");

        btnFechar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose(); // Fecha a janela
            }
        });
    }
    
    private void createResourceManager() {
    	localResourceManager = new LocalResourceManager(JFaceResources.getResources(),shell);
    }

    // Método auxiliar para exibir mensagens na tela
    private void showMessage(String message) {
        Shell dialog = new Shell(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setSize(300, 150);
        dialog.setText("Mensagem");
        org.eclipse.swt.widgets.Label lblMessage = new org.eclipse.swt.widgets.Label(dialog, SWT.NONE);
        lblMessage.setBounds(20, 20, 260, 15);
        lblMessage.setText(message);

        Button btnOk = new Button(dialog, SWT.PUSH);
        btnOk.setBounds(100, 70, 80, 30);
        btnOk.setText("OK");
        btnOk.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                dialog.close();
            }
        });
        dialog.open();
        Display display = Display.getDefault();
        while (!dialog.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
}
