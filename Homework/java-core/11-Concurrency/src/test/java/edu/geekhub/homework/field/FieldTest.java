package edu.geekhub.homework.field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    public Field field;

    @BeforeEach
    void setUp(){
        field = new Field(40,40);
    }

    @Test
    void set_Fieldable_And_Get_Field_Value(){
        field.setFieldable(1,1,new BlankField());

        assertEquals(" ",field.getFieldable(1,1).getFieldValue());

    }

    @Test
    void test_Marking_All_Fields_Blank(){
        field.markAllFieldsBlank(field);

        assertEquals(" ",field.getFieldable(2,2).getFieldValue());
    }

    @Test
    void test_Generate_Start_position(){
        field.generateStartField(field);

        assertEquals("s",field.getFieldable(21,21).getFieldValue());
    }

    @Test
    void generate_Bottom_Field_With_Empty_Fields(){
        field.markAllFieldsBlank(field);
        field.generateStartField(field);
        field.addBottomField(field,new EmptyField());

        assertEquals("_",field.getFieldable(24,21).getFieldValue());
    }

    @Test
    void generate_Top_Field_With_Empty_Fields(){
        field.markAllFieldsBlank(field);
        field.generateStartField(field);
        field.addTopField(field,new EmptyField());

        assertEquals("_",field.getFieldable(19,21).getFieldValue());
    }

    @Test
    void generate_Left_Field_With_Empty_Fields(){
        field.markAllFieldsBlank(field);
        field.generateStartField(field);
        field.addLeftField(field,new EmptyField());

        assertEquals("_",field.getFieldable(21,18).getFieldValue());
    }

    @Test
    void generate_Right_Field_With_Empty_Fields(){
        field.markAllFieldsBlank(field);
        field.generateStartField(field);
        field.addRightField(field,new EmptyField());

        assertEquals("_",field.getFieldable(21,24).getFieldValue());
    }


}