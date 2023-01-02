package com.tkato.myKanBan.service;

import com.tkato.myKanBan.exception.ProjectAlreadyExists;
import com.tkato.myKanBan.exception.ProjectNotFoundException;
import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.model.User;
import com.tkato.myKanBan.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    private ProjectService uut;
    @Mock private UserService userService;
    @Mock private ProjectRepository projectRepository;

    private static final String TESTPID = "ABCD";
    private static final String TESTUNAME = "johndoe";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        uut = new ProjectService(userService, projectRepository);
    }

    @Test
    void canGetAllProjects() {
        uut.getAllProjects();
        verify(projectRepository).findAll();
    }

    @Test
    void canGetProject() {
        Project project = new Project();
        User user = new User();
        Set<Project> projects = new HashSet<>();
        projects.add(project);
        user.setProject(projects);

        when(projectRepository.findByProjectIdentifier(TESTPID)).thenReturn(project);
        when(userService.loadUserByUsername(TESTUNAME)).thenReturn(user);
        uut.getProject(TESTPID, TESTUNAME);

        verify(userService).loadUserByUsername(TESTUNAME);
        verify(projectRepository).findByProjectIdentifier(TESTPID);
    }

    @Test
    void canThrowProjectNotFoundExceptionWhenNoProject() {
        ProjectNotFoundException ex = assertThrows(ProjectNotFoundException.class, () -> uut.getProject(TESTPID, TESTUNAME));
        assertTrue(ex.getMessage().contains("Could not find project with PID"));
    }

    @Test
    void canThrowProjectNotFoundExceptionWhenAccessingUnauthorizedProject() {
        Project project = new Project();
        User user = new User();

        when(projectRepository.findByProjectIdentifier(TESTPID)).thenReturn(project);
        when(userService.loadUserByUsername(TESTUNAME)).thenReturn(user);

        ProjectNotFoundException ex = assertThrows(ProjectNotFoundException.class, () -> uut.getProject(TESTPID, TESTUNAME));
        assertFalse(ex.getMessage().contains("Could not find project with PID"));
        assertEquals(ex.getMessage(), "Project not found in your account");
    }

    @Test
    void addNewProject() {
        Project project = new Project();
        project.setProjectIdentifier(TESTPID);
        User user = new User();
        user.setUsername(TESTUNAME);

        when(projectRepository.findByProjectIdentifier(TESTPID)).thenReturn(project);
        when(userService.loadUserByUsername(TESTUNAME)).thenReturn(user);

        uut.addNewProject(project, TESTUNAME);
        verify(projectRepository).save(project);
    }

    @Test
    void addExistingProjectThrowsException() {
        Project project = new Project();
        User user = new User();

        user.setUsername(TESTUNAME);
        Set<User> userList = new HashSet<>();
        userList.add(user);
        project.setUser(userList);
        project.setProjectIdentifier(TESTPID);

        when(projectRepository.findByProjectIdentifier(TESTPID)).thenReturn(project);

        ProjectService spy = spy(uut);
        ProjectAlreadyExists ex = assertThrows(ProjectAlreadyExists.class, () -> spy.addNewProject(project, TESTUNAME));
        assertEquals(ex.getMessage(), "Project already exists in your account");
    }

    @Test
    void modifyProject() {
        Project project = new Project();
        project.setProjectIdentifier(TESTPID);

        ProjectService projectServiceSpy = Mockito.spy(uut);
        doReturn(project).when(projectServiceSpy).getProject(TESTPID, TESTUNAME);

        projectServiceSpy.modifyProject(project, TESTUNAME);
        verify(projectRepository).save(project);
    }

    @Test
    void deleteProjectById() {
        Project project = new Project();
        project.setId(1L);
        project.setProjectIdentifier(TESTPID);
        ProjectService projectServiceSpy = Mockito.spy(uut);
        doReturn(project).when(projectServiceSpy).getProject(TESTPID, TESTUNAME);

        projectServiceSpy.deleteProjectById(TESTPID, TESTUNAME);
        verify(projectRepository).deleteById(1L);
    }
}