package view.doacao;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import banco.doacaoBanco;
import model.Doacao;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;

public class consultarDoacao {
    protected Shell shell;
    private Text textCodDoacao;
    private Text textResultado;
    private doacaoBanco doacaoBanco;
    private LocalResourceManager localResourceManager;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            consultarDoacao window = new consultarDoacao();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public consultarDoacao() {
        doacaoBanco = new doacaoBanco(); // Inicializa o objeto para interagir com o banco
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
        shell.setSize(600, 450);
        shell.setText("Consultar Doação");

        // Define a imagem de fundo
        Image backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petDo.jpg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE); // Força a herança de fundo para componentes filhos

        // Campo para o código da doação
        textCodDoacao = new Text(shell, SWT.BORDER);
        textCodDoacao.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textCodDoacao.setBounds(50, 80, 100, 25);

        // Campo para os resultados
        textResultado = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        textResultado.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textResultado.setBounds(50, 150, 500, 200);

        // Botão "Consultar"
        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnConsultar.setBounds(400, 370, 120, 30);
        btnConsultar.setText("Consultar");

        // Botão "Cancelar"
        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(50, 370, 120, 30);
        btnCancelar.setText("Cancelar");

        // Rótulos
        Label lblDigiteCodigo = new Label(shell, SWT.BORDER);
        lblDigiteCodigo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblDigiteCodigo.setText("Digite o código da doação:");
        lblDigiteCodigo.setBounds(50, 50, 144, 17);

        Label lblResultados = new Label(shell, SWT.NONE);
        lblResultados.setText("Resultados:");
        lblResultados.setBounds(50, 125, 100, 25);

        Label lblTitulo = new Label(shell, SWT.NONE);
        lblTitulo.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 16, SWT.BOLD)));
        lblTitulo.setText("Consultar Doação");
        lblTitulo.setBounds(200, 10, 200, 30);

        // Evento para o botão "Consultar"
        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    int codDoacao = Integer.parseInt(textCodDoacao.getText());

                    // Realiza a consulta
                    Doacao doacao = new Doacao(null, null); // Data e Local inicialmente nulos
                    doacao.setCodDoacao(codDoacao);

                    Doacao resultado = doacaoBanco.consultarDoacao(doacao);

                    if (resultado != null) {
                        textResultado.setText("Código: " + resultado.getCodDoacao() + "\n" +
                                              "Data: " + resultado.getDataDoacao() + "\n" +
                                              "Local: " + resultado.getLocalDoacao());
                    } else {
                        textResultado.setText("Doação não encontrada.");
                    }
                } catch (NumberFormatException ex) {
                    textResultado.setText("Erro: Código inválido.");
                } catch (Exception ex) {
                    textResultado.setText("Erro ao consultar a doação: " + ex.getMessage());
                }
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                textCodDoacao.setText("");
                textResultado.setText("");
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
