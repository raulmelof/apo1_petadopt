package view.doador;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.doadorBanco;
import model.Doador;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.RGB;

public class inserirDoador {
    protected Shell shell;
    private Text txtCpfDoador;
    private Text txtNomeDoador;
    private Text txtTelDoador;
    private Text txtEnderecoDoador;
    private doadorBanco doadorBanco;
    private LocalResourceManager localResourceManager;
    private Image backgroundImage;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            inserirDoador window = new inserirDoador();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public inserirDoador() {
        doadorBanco = new doadorBanco(); // Inicializa o objeto para interagir com o banco
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
        // Libera a imagem de fundo ao fechar
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
        shell.setText("Inserir Doador");

        // Carrega a imagem de fundo
        backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petDoador.jpg");
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        // Adiciona o PaintListener para desenhar a imagem de fundo
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

        // Campos de texto para entrada de dados
        txtCpfDoador = new Text(shell, SWT.BORDER);
        txtCpfDoador.setBounds(171, 128, 250, 30);

        txtNomeDoador = new Text(shell, SWT.BORDER);
        txtNomeDoador.setBounds(171, 164, 250, 30);

        txtTelDoador = new Text(shell, SWT.BORDER);
        txtTelDoador.setBounds(171, 200, 250, 30);

        txtEnderecoDoador = new Text(shell, SWT.BORDER);
        txtEnderecoDoador.setBounds(171, 236, 250, 30);

        // Botão para salvar
        Button btnSalvar = new Button(shell, SWT.NONE);
        btnSalvar.setText("Salvar");
        btnSalvar.setBounds(403, 352, 97, 34);
        btnSalvar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnSalvar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));

        // Botão para cancelar
        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(92, 352, 97, 34);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        
        Label lblInsiraOCpf = new Label(shell, SWT.NONE);
        lblInsiraOCpf.setBounds(100, 128, 65, 15);
        lblInsiraOCpf.setText("Insira o CPF:");
        
        Label lblInsiraONome = new Label(shell, SWT.NONE);
        lblInsiraONome.setBounds(90, 164, 75, 15);
        lblInsiraONome.setText("Insira o nome:");
        
        Label lblInsiraOTelefone = new Label(shell, SWT.NONE);
        lblInsiraOTelefone.setBounds(78, 200, 87, 15);
        lblInsiraOTelefone.setText("Insira o telefone:");
        
        Label lblInsiraOEndereco = new Label(shell, SWT.NONE);
        lblInsiraOEndereco.setBounds(72, 236, 93, 15);
        lblInsiraOEndereco.setText("Insira o endereço:");
        
        Label lblCadastreseComoDoador = new Label(shell, SWT.NONE);
        lblCadastreseComoDoador.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblCadastreseComoDoador.setText("Cadastre Doador");
        lblCadastreseComoDoador.setBounds(226, 37, 151, 24);

        // Ações ao clicar no botão "Salvar"
        btnSalvar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String cpfString = txtCpfDoador.getText().trim(); // Pega o CPF como string

                // Validação: Verifica se o CPF tem exatamente 11 dígitos e é numérico
                if (cpfString.length() != 11 || !cpfString.matches("\\d+")) {
                    System.out.println("Erro: CPF deve ter 11 dígitos numéricos.");
                    return;  // Retorna ou exibe a mensagem de erro
                }

                // Cria o objeto doador
                Doador doador = new Doador();
                doador.setCpfDoador(cpfString);  // Define o CPF como int
                doador.setNomeDoador(txtNomeDoador.getText());
                doador.setTelDoador(txtTelDoador.getText());
                doador.setEnderecoDoador(txtEnderecoDoador.getText());

                // Chama o método para inserir o doador no banco
                doadorBanco.inserirDoador(doador);

                // Limpa os campos de texto após inserir
                txtCpfDoador.setText("");
                txtNomeDoador.setText("");
                txtTelDoador.setText("");
                txtEnderecoDoador.setText("");
            }
        });

        // Ações ao clicar no botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos de texto
                txtCpfDoador.setText("");
                txtNomeDoador.setText("");
                txtTelDoador.setText("");
                txtEnderecoDoador.setText("");
                shell.dispose(); // Fecha a janela
            }
        });
    }

    // Função para criar e gerenciar os recursos como fontes
    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
