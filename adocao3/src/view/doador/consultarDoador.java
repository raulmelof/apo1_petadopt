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
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

public class consultarDoador {
    protected Shell shell;
    private Text textCpfDoador;
    private Text textResultado;
    private doadorBanco doadorBanco;
    private LocalResourceManager localResourceManager;
    private Image backgroundImage;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            consultarDoador window = new consultarDoador();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public consultarDoador() {
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
        // Libera a imagem ao fechar a janela
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
        shell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        shell.setSize(600, 450);
        shell.setText("Consultar Doador");

        // Carrega a imagem de fundo
        backgroundImage = new Image(shell.getDisplay(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petDoador.jpg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE); // Permite herança de fundo para widgets filhos

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

        textCpfDoador = new Text(shell, SWT.BORDER);
        textCpfDoador.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textCpfDoador.setBounds(298, 96, 97, 21);

        textResultado = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        textResultado.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textResultado.setBounds(93, 192, 410, 120);

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnConsultar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnConsultar.setBounds(410, 352, 97, 34);
        btnConsultar.setText("Consultar");

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(71, 352, 97, 34);
        btnCancelar.setText("Cancelar");
        
        Label lblResultado = new Label(shell, SWT.NONE);
        lblResultado.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblResultado.setBounds(71, 147, 55, 15);
        lblResultado.setText("Resultado:");
        
        Label lblDigiteOCpf = new Label(shell, SWT.NONE);
        lblDigiteOCpf.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblDigiteOCpf.setBounds(71, 99, 204, 15);
        lblDigiteOCpf.setText("Digite o CPF do doador para consultar:");
        
        Label lblConsultarDoador = new Label(shell, SWT.NONE);
        lblConsultarDoador.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblConsultarDoador.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblConsultarDoador.setBounds(200, 10, 159, 24);
        lblConsultarDoador.setText("Consultar Doador");

        // Evento para o botão "Consultar"
        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    String cpfDoador = textCpfDoador.getText();

                    // Criar o objeto Doador para realizar a consulta
                    Doador doador = new Doador();
                    doador.setCpfDoador(cpfDoador);

                    // Chamar o método consultarDoador do doadorBanco
                    Doador resultado = doadorBanco.consultarDoador(doador);

                    // Exibir os dados retornados na interface
                    if (resultado != null) {
                        textResultado.setText("CPF: " + resultado.getCpfDoador() + "\n" +
                                              "Nome: " + resultado.getNomeDoador() + "\n" +
                                              "Telefone: " + resultado.getTelDoador() + "\n" +
                                              "Endereço: " + resultado.getEnderecoDoador());
                    } else {
                        textResultado.setText("Doador não encontrado.");
                    }
                } catch (Exception ex) {
                    textResultado.setText("Erro ao consultar o doador: " + ex.getMessage());
                }
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos de texto
                textCpfDoador.setText("");
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
