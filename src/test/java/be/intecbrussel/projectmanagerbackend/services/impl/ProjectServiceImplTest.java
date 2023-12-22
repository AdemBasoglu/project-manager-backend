package be.intecbrussel.projectmanagerbackend.services.impl;


import be.intecbrussel.projectmanagerbackend.models.Project;
import be.intecbrussel.projectmanagerbackend.models.User;
import be.intecbrussel.projectmanagerbackend.repositories.ProjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project mockProject;
    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = new User("a@b.com", "123", "adem", "bas");
         mockProject = new Project(1L,"Test Project","Test Project Description", mockUser);
    }
    @AfterEach
    public void tearDown() {
        projectRepository.deleteAll();
    }

    @Test
    public void givenProject_whenAddProject_thenAddSavedSuccessfully() {
        // Given
        given(userService.getUser("a@b.com")).willReturn(mockUser);
        given(projectRepository.save(any(Project.class))).willReturn(mockProject);

        // When
        Project result = projectService.addProject("Test Project", "a@b.com");

        // Then
        assertNotNull(result);
        assertEquals("Test Project", result.getName());
        assertThat(result.getDescritption()).isEqualTo("Test Project Description");
        verify(projectRepository).save(any(Project.class));
    }

    @Test
    public void givenProjectId_whenFindProjectId_ReturnProjectObj() {
        // Given
        given(projectRepository.findById(1L)).willReturn(Optional.of(mockProject));

        // When
        Project result = projectService.getProject(1L);

        // Then
        assertNotNull(result);
        assertEquals(mockProject, result);
        verify(projectRepository).findById(1L);
    }



    @Test
    public void givenProjectId_whenFindProjectId_UpdateProjectObj() {
        // Given
        given(projectRepository.findById(1L)).willReturn(Optional.of(mockProject));
        given(projectRepository.save(any(Project.class))).willReturn(mockProject);

        // When
        Project result = projectService.updateProject("Updated Project", 1L);

        // Then
        assertNotNull(result);
        assertEquals("Updated Project", result.getName());
        verify(projectRepository).findById(1L);
        verify(projectRepository).save(any(Project.class));
    }
    @Test
    public void givenProjectId_whenFindProjectId_DeleteProjectObj() {
        // Hazırlık aşaması
        //Long projectId = 1L;
        mockProject = new Project("new project",mockUser);
       mockProject.setId(1L);

        // given
        given(projectRepository.findById(1L)).willReturn(Optional.ofNullable(mockProject));
        willDoNothing().given(projectRepository).deleteById(mockProject.getId());

        // when
        projectService.deleteProject(1L);

        // then
        // deleteById çağrısının bir kez yapıldığını kontrol ediyoruz
        verify(projectRepository, times(1)).deleteById(1L);

        // Mockito ile yapılan tüm etkileşimleri kontrol ediyoruz
        verify(projectRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(projectRepository);
    }
}



