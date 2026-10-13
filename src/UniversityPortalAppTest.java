import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class UniversityPortalAppTest {

    // 1. Test Main Application Window Properties
    @Test
    public void testAppInitializationAndTitle() {
        UniversityPortalApp app = new UniversityPortalApp();
        assertEquals("University Academic Services Portal", app.getTitle());
        assertEquals(900, app.getWidth());
        assertEquals(600, app.getHeight());
        assertEquals(JFrame.EXIT_ON_CLOSE, app.getDefaultCloseOperation());
        app.dispose();
    }

    // 2. Test Main Layout & Component Container Structure
    @Test
    public void testMainLayoutAndContainers() {
        UniversityPortalApp app = new UniversityPortalApp();
        assertTrue(app.getLayout() instanceof BorderLayout, "Main frame must use BorderLayout");

        Component[] components = app.getContentPane().getComponents();
        assertTrue(components.length >= 2, "Main frame should contain at least the sidebar and card panel");
        app.dispose();
    }

    // 3. Test Sidebar Structure & Navigation Buttons
    @Test
    public void testSidebarPanelComponents() {
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        SidebarPanel sidebar = new SidebarPanel(cardLayout, cardPanel);

        // Sidebar uses GridLayout(6, 1, 5, 5) -> Title + 5 navigation buttons = 6 components
        Component[] sidebarComps = sidebar.getComponents();
        assertEquals(6, sidebarComps.length, "Sidebar must contain 1 title label and 5 navigation buttons");

        assertTrue(sidebarComps[0] instanceof JLabel, "First component should be the Portal Menu label");
        assertTrue(sidebarComps[1] instanceof JButton, "Navigation elements must be buttons");

        // Verify button text labels
        assertEquals("Course Registration", ((JButton) sidebarComps[1]).getText());
        assertEquals("Fee Payment", ((JButton) sidebarComps[2]).getText());
        assertEquals("Examination Results", ((JButton) sidebarComps[3]).getText());
        assertEquals("Hostel Applications", ((JButton) sidebarComps[4]).getText());
        assertEquals("Library Services", ((JButton) sidebarComps[5]).getText());
    }

    // 4. Test Course Panel Content
    @Test
    public void testCoursePanelContent() {
        CoursePanel panel = new CoursePanel();
        boolean foundText = false;
        for (Component c : panel.getComponents()) {
            if (c instanceof JScrollPane) {
                JTextArea area = (JTextArea) ((JScrollPane) c).getViewport().getView();
                if (area.getText().contains("Manage your semester enrollment")) {
                    foundText = true;
                }
            }
        }
        assertTrue(foundText, "CoursePanel should display enrollment information");
    }

    // 5. Test Fee Panel Content & Balance
    @Test
    public void testFeePanelBalance() {
        FeePanel panel = new FeePanel();
        boolean foundBalance = false;
        for (Component c : panel.getComponents()) {
            if (c instanceof JScrollPane) {
                JTextArea area = (JTextArea) ((JScrollPane) c).getViewport().getView();
                if (area.getText().contains("$2,450.00")) {
                    foundBalance = true;
                }
            }
        }
        assertTrue(foundBalance, "FeePanel should display the correct tuition balance");
    }

    // 6. Test Exam Panel Content & GPA
    @Test
    public void testExamPanelGPA() {
        ExamPanel panel = new ExamPanel();
        boolean foundGPA = false;
        for (Component c : panel.getComponents()) {
            if (c instanceof JScrollPane) {
                JTextArea area = (JTextArea) ((JScrollPane) c).getViewport().getView();
                if (area.getText().contains("3.78")) {
                    foundGPA = true;
                }
            }
        }
        assertTrue(foundGPA, "ExamPanel should display the correct cumulative GPA");
    }

    // 7. Test Hostel and Library Panels
    @Test
    public void testHostelAndLibraryPanels() {
        HostelPanel hostel = new HostelPanel();
        LibraryPanel library = new LibraryPanel();

        assertNotNull(hostel, "HostelPanel should initialize successfully");
        assertNotNull(library, "LibraryPanel should initialize successfully");
    }
}