package view.vacinas;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.time.LocalDate;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import banco.vacinasBanco; // Banco de dados para manipulação de vacinas
import model.Vacinas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.graphics.Image;

public class registrarVacinas {

    protected Shell shell;
    private Text textNomeVacina;
    private Text textCodProntuario;
    private vacinasBanco vacinasBanco;

    public static void main(String[] args) {
        try {
            registrarVacinas window = new registrarVacinas();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public registrarVacinas() {
        vacinasBanco = new vacinasBanco(); // Instancia a classe do banco de dados para interagir com o banco
    }

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

    protected void createContents() {
        shell = new Shell();
        shell.setSize(500, 400);
        shell.setText("Registrar Vacinas");

        // Configuração da imagem de fundo
        Image backgroundImage = new Image(Display.getCurrent(), "C:/Users/zelio/Downloads/adocaoJavaatt6/fotos/petsVacin.jpg");
        shell.setBackgroundImage(backgroundImage);
        shell.setBackgroundMode(SWT.INHERIT_FORCE);

        Label lblTitulo = new Label(shell, SWT.CENTER);
        lblTitulo.setText("Registrar Vacinas");
        lblTitulo.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        lblTitulo.setBounds(0, 10, 484, 25);

        // Label para Nome da Vacina
        Label lblNomeVacina = new Label(shell, SWT.NONE);
        lblNomeVacina.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblNomeVacina.setText("Nome da Vacina:");
        lblNomeVacina.setBounds(89, 182, 89, 15);

        // Campo de texto para Nome da Vacina
        textNomeVacina = new Text(shell, SWT.BORDER);
        textNomeVacina.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textNomeVacina.setBounds(184, 179, 200, 20);

        // Label para Data da Vacina
        Label lblDataVacina = new Label(shell, SWT.NONE);
        lblDataVacina.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblDataVacina.setText("Data da Vacina:");
        lblDataVacina.setBounds(98, 148, 80, 15);

        // Label para Código do Prontuário
        Label lblCodProntuario = new Label(shell, SWT.NONE);
        lblCodProntuario.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        lblCodProntuario.setText("Código do Prontuário:");
        lblCodProntuario.setBounds(58, 222, 120, 17);

        // Campo de texto para Código do Prontuário
        textCodProntuario = new Text(shell, SWT.BORDER);
        textCodProntuario.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        textCodProntuario.setBounds(184, 219, 200, 20);

        // Botão de Registro
        Button btnRegistrar = new Button(shell, SWT.NONE);
        btnRegistrar.setText("Registrar Vacina");
        btnRegistrar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        btnRegistrar.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        btnRegistrar.setBounds(328, 299, 120, 30);

        // Botão de Cancelamento
        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setText("Cancelar");
        btnCancelar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        btnCancelar.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        btnCancelar.setBounds(36, 299, 120, 30);

        DateTime dateDataVacina = new DateTime(shell, SWT.BORDER);
        dateDataVacina.setBounds(184, 139, 120, 24);

        // Evento para o botão "Registrar Vacina"
        btnRegistrar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    // Obtém os dados inseridos na interface
                    String nomeVacina = textNomeVacina.getText();
                    String codProntuarioStr = textCodProntuario.getText();

                    if (nomeVacina == null || nomeVacina.isEmpty()) {
                        throw new IllegalArgumentException("O nome da vacina não pode ser vazio.");
                    }

                    if (codProntuarioStr == null || codProntuarioStr.isEmpty()) {
                        throw new IllegalArgumentException("O código do prontuário não pode ser vazio.");
                    }

                    Integer codProntuario = Integer.parseInt(codProntuarioStr);

                    // Obtém a data selecionada no DateTime
                    LocalDate data = LocalDate.of(dateDataVacina.getYear(),
                            dateDataVacina.getMonth() + 1, // Ajuste necessário para o DateTime
                            dateDataVacina.getDay());

                    // Cria o objeto Vacinas
                    Vacinas vacinas = new Vacinas();
                    vacinas.setNomeVacina(nomeVacina);
                    vacinas.setDataVacina(data);
                    vacinas.setCodProntuario(codProntuario);

                    // Registra a vacina no banco
                    vacinasBanco.registrarVacinas(vacinas);

                    // Exibe mensagem de sucesso
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                    messagebox.setMessage("Vacina registrada com sucesso!");
                    messagebox.open();
                } catch (IllegalArgumentException ex) {
                    // Mensagem de erro caso os campos sejam inválidos
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro: " + ex.getMessage());
                    messagebox.open();
                } catch (Exception ex) {
                    // Exibe mensagem de erro em caso de falha no processo
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                    messagebox.setMessage("Erro ao registrar a vacina: " + ex.getMessage());
                    messagebox.open();
                }
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Limpa os campos e fecha a janela
                textNomeVacina.setText("");
                textCodProntuario.setText("");
                shell.dispose();
            }
        });
    }
}