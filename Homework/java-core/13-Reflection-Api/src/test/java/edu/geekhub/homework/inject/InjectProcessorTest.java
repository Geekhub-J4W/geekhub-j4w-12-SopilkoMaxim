package edu.geekhub.homework.inject;

import edu.geekhub.homework.GeekHubCourse;
import edu.geekhub.homework.Values;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InjectProcessorTest {


    @Test
    public void testProcessWithHomeworkValues() throws IllegalAccessException, InstantiationException {
        GeekHubCourse expected = new GeekHubCourse();
        Class<GeekHubCourse> clazz = (Class<GeekHubCourse>) expected.getClass();
        for (Field declaredField : clazz.getDeclaredFields()) {
            declaredField.setAccessible(true);
            if (declaredField.getName().equals("name"))
                declaredField.set(expected,"ghjfw");
            if(declaredField.getName().equals("mentorName"))
                declaredField.set(expected,"Uncle Bob");
            if(declaredField.getName().equals("registrations"))
                declaredField.set(expected,40000);
        }
        InjectProcessor injectProcessor = new InjectProcessor();
        ArrayList<Values> example = new ArrayList<>();
        example.add(new Values("courseName","ghjfw"));
        example.add(new Values("courseDuration","15"));
        example.add(new Values("mentor","Uncle Bob"));
        example.add(new Values("registrations","40000"));


        try (MockedStatic mocked = mockStatic(FileReaderConverter.class)){
            mocked.when(FileReaderConverter::convertFileToEntity).thenReturn(example);

            Object resived = injectProcessor.process(new GeekHubCourse());

            assertEquals(resived,expected);
        }
    }

    @Test
    public void testProcessWithAnotherValues() throws IllegalAccessException, InstantiationException {
        GeekHubCourse expected = new GeekHubCourse();
        Class<GeekHubCourse> clazz = (Class<GeekHubCourse>) expected.getClass();
        for (Field declaredField : clazz.getDeclaredFields()) {
            declaredField.setAccessible(true);
            if (declaredField.getName().equals("name"))
                declaredField.set(expected,"Name");
            if(declaredField.getName().equals("duration"))
                declaredField.set(expected,15);
            if(declaredField.getName().equals("mentorName"))
                declaredField.set(expected,"Mentor Name");
            if(declaredField.getName().equals("registrations"))
                declaredField.set(expected,40000);
        }
        InjectProcessor injectProcessor = new InjectProcessor();
        ArrayList<Values> example = new ArrayList<>();
        example.add(new Values("courseName","Name"));
        example.add(new Values("duration","15"));
        example.add(new Values("mentor","Mentor Name"));
        example.add(new Values("registrations","40000"));


        try (MockedStatic mocked = mockStatic(FileReaderConverter.class)){
            mocked.when(FileReaderConverter::convertFileToEntity).thenReturn(example);

            Object resived = injectProcessor.process(new GeekHubCourse());

            assertEquals(resived,expected);
        }
    }

    @Test
    public void testNewClassWithNewAnnotations() throws IllegalAccessException, InstantiationException {
        TestClassOfGeekHubCourse expected = new TestClassOfGeekHubCourse();
        Class<TestClassOfGeekHubCourse> clazz = (Class<TestClassOfGeekHubCourse>) expected.getClass();
        for (Field declaredField : clazz.getDeclaredFields()) {
            declaredField.setAccessible(true);
            if (declaredField.getName().equals("name"))
                declaredField.set(expected,"Name");
            if(declaredField.getName().equals("duration"))
                declaredField.set(expected,15);
            if(declaredField.getName().equals("description"))
                declaredField.set(expected,"some description");
            if(declaredField.getName().equals("mentorName"))
                declaredField.set(expected,"Mentor Name");
            if(declaredField.getName().equals("registrations"))
                declaredField.set(expected,40000);
        }
        InjectProcessor injectProcessor = new InjectProcessor();
        ArrayList<Values> example = new ArrayList<>();
        example.add(new Values("name","Name"));
        example.add(new Values("duration","15"));
        example.add(new Values("description","some description"));
        example.add(new Values("mentor","Mentor Name"));
        example.add(new Values("registrations","40000"));


        try (MockedStatic mocked = mockStatic(FileReaderConverter.class)){
            mocked.when(FileReaderConverter::convertFileToEntity).thenReturn(example);

            Object resived = injectProcessor.process(new TestClassOfGeekHubCourse());

            assertEquals(resived,expected);
        }
    }

}