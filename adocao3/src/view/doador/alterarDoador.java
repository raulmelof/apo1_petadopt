package view.doador;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;

import banco.doadorBanco;
import model.Doador;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;

public class alterarDoador {

    protected Shell shell;
    private Text textCpfDoador;
    private Text textNomeDoador;
    private Text textTelDoador;
    private Text textEnderecoDoador;
    private doadorBanco doadorBanco;
    private LocalResourceManager localResourceManager;
    private Image backgroundImage;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            alterarDoador window = new alterarDoador();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public alterarDoador() {
        doadorBanco = new doadorBanco();
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
        // Libere a imagem ao fechar o shell
        if (backgroundImage != null && !backgroundImage.isDisposed()) {
            backgroundImage.dispose();
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        createResourceManager();
        shell.setSize(600, 450);
        shell.setText("Alterar Doador");

        // Carrega a imagem de fundo
        backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petDoador.jpg");
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        // Adiciona um listener para desenhar a imagem de fundo
        shell.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                if (backgroundImage != null) {
                    e.gc.drawImage(backgroundImage, 0, 0, 
                                   backgroundImage.getBounds().width, backgroundImage.getBounds().height, 
                                   0, 0, shell.getBounds().width, shell.getBounds().height);
                }
            }
        });

        textCpfDoador = new Text(shell, SWT.BORDER);
        textCpfDoador.setBounds(306, 96, 97, 21);

        textNomeDoador = new Text(shell, SWT.BORDER);
        textNomeDoador.setBounds(176, 194, 241, 30);

        textTelDoador = new Text(shell, SWT.BORDER);
        textTelDoador.setBounds(176, 230, 241, 30);

        textEnderecoDoador = new Text(shell, SWT.BORDER);
        textEnderecoDoador.setBounds(176, 266, 241, 30);

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(65, 346, 97, 34);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));

        Button btnOK = new Button(shell, SWT.NONE);
        btnOK.setText("OK");
        btnOK.setBounds(418, 346, 97, 34);
        btnOK.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnOK.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));

        Label lblEndereco = new Label(shell, SWT.NONE);
        lblEndereco.setBounds(115, 266, 55, 15);
        lblEndereco.setText("Endereço:");

        Label lblTelefone = new Label(shell, SWT.NONE);
        lblTelefone.setBounds(123, 230, 47, 15);
        lblTelefone.setText("Telefone:");

        Label lblNomeDoDoador = new Label(shell, SWT.NONE);
        lblNomeDoDoador.setBounds(73, 194, 97, 15);
        lblNomeDoDoador.setText("Nome do doador:");

        Label lblInsiraOsNovos = new Label(shell, SWT.NONE);
        lblInsiraOsNovos.setBounds(141, 149, 116, 15);
        lblInsiraOsNovos.setText("Insira os novos dados:");

        Label lblDigiteOCpf = new Label(shell, SWT.NONE);
        lblDigiteOCpf.setBounds(65, 96, 221, 15);
        lblDigiteOCpf.setText("Digite o CPF do doador que deseja alterar:");

        Label lblAlterarOsDados = new Label(shell, SWT.NONE);
        lblAlterarOsDados.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblAlterarOsDados.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblAlterarOsDados.setBounds(162, 26, 247, 21);
        lblAlterarOsDados.setText("Alterar os dados do Doador");

        btnOK.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Doador doador = new Doador();
                doador.setCpfDoador(textCpfDoador.getText());
                doador.setNomeDoador(textNomeDoador.getText());
                doador.setTelDoador(textTelDoador.getText());
                doador.setEnderecoDoador(textEnderecoDoador.getText());

                doadorBanco.alterarDoador(doador);

                MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                messagebox.setMessage("Doador alterado com sucesso");
                messagebox.open();
            }
        });

        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                textNomeDoador.setText("");
                textTelDoador.setText("");
                textEnderecoDoador.setText("");
                shell.dispose(); // Fecha a janela ao cancelar
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
