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
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.resource.FontDescriptor;

public class removerDoador {
    protected Shell shell;
    private Text textCpfDoador;
    private doadorBanco doadorBanco;
    private LocalResourceManager localResourceManager;
    private Label lblDigiteOCpf;
    private Label lblRemover;
    private Image backgroundImage;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            removerDoador window = new removerDoador();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public removerDoador() {
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
        shell.setText("Remover Doador");

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

        textCpfDoador = new Text(shell, SWT.BORDER);
        textCpfDoador.setBounds(318, 161, 120, 21);

        Button btnRemover = new Button(shell, SWT.NONE);
        btnRemover.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnRemover.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnRemover.setBounds(441, 344, 97, 34);
        btnRemover.setText("Remover");

        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setForeground(localResourceManager.createColor(new RGB(255, 255, 255)));
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setBounds(55, 344, 97, 34);
        btnCancelar.setText("Cancelar");
        
        lblDigiteOCpf = new Label(shell, SWT.NONE);
        lblDigiteOCpf.setBounds(113, 164, 199, 15);
        lblDigiteOCpf.setText("Digite o CPF do doador para remover:");
        
        lblRemover = new Label(shell, SWT.NONE);
        lblRemover.setFont(localResourceManager.create(FontDescriptor.createFrom("@Microsoft JhengHei UI", 14, SWT.NORMAL)));
        lblRemover.setBounds(223, 41, 153, 21);
        lblRemover.setText("Remover Doador");

        // Evento para o botão "Remover"
        btnRemover.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    String cpfDoador = textCpfDoador.getText();

                    // Validação básica do CPF
                    if (cpfDoador == null || cpfDoador.isEmpty()) {
                        throw new IllegalArgumentException("CPF não pode ser vazio.");
                    }

                    // Criar o objeto Doador
                    Doador doador = new Doador();
                    doador.setCpfDoador(cpfDoador);

                    // Chama o método de removerDoador que invoca o procedimento no banco
                    doadorBanco.removerDoador(doador);

                    // Exibe MessageBox de sucesso
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                    messagebox.setMessage("Doador com CPF " + cpfDoador + " removido com sucesso.");
                    messagebox.open();
                } catch (IllegalArgumentException ex) {
                    // Exibe MessageBox de erro se o CPF for inválido
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro: " + ex.getMessage());
                    messagebox.open();
                } catch (Exception ex) {
                    // Exibe MessageBox de erro em caso de falha no processo de remoção
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro ao remover o doador: " + ex.getMessage());
                    messagebox.open();
                }
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos de texto
                textCpfDoador.setText("");

                // Se necessário, pode fechar a janela ao cancelar
                shell.dispose();
            }
        });
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}
