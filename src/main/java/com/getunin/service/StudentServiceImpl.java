package com.getunin.service;

import com.getunin.dto.JwtRequest;
import com.getunin.dto.StudentRequest;
import com.getunin.entity.Parameter;
import com.getunin.entity.Student;
import com.getunin.entity.User;
import com.getunin.exception.listexceptions.ConflictException;
import com.getunin.exception.listexceptions.NotFoundException;
import com.getunin.repository.StudentRepository;
import com.getunin.service.interfaces.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {


    private final JwtUserDetailsService jwtUserDetailsService;

    private final StudentRepository studentRepository;

    public StudentServiceImpl(JwtUserDetailsService jwtUserDetailsService, StudentRepository studentRepository) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.studentRepository = studentRepository;
    }


    @Override
    public Student createStudent(StudentRequest request) {

        User user = createJwtRequest(request, null);

        Student student = new Student();
        student.setUser(user);
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(StudentRequest request,Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()){
            User userUpdate = createJwtRequest(request,student.get().getUser().getId() );

            Student studentUpdate = student.get();

            studentUpdate.setUser(userUpdate);

            return studentRepository.save(studentUpdate);

        }else {
            throw new NotFoundException("id de estudiante no encontrado");
        }
    }

    @Override
    public Student getStudentById(Long id) {

        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()){
            return student.get();
        }else {
         throw new NotFoundException("id de estudiante no encontrado");
        }
    }

    @Override
    public Student getStudentByEmail(String email) {

        Optional<Student> student = Optional.ofNullable(studentRepository.findStudentByUser_Email(email));

        if(student.isPresent()){
            return student.get();
        }else {
            throw new NotFoundException("id de estudiante no encontrado");
        }
    }


    private User createJwtRequest(StudentRequest request, Long id){
        JwtRequest jwtRequest = new JwtRequest();

        jwtRequest.setIdentificationType(request.getIdentificationType());
        jwtRequest.setIdentification(request.getIdentification());
        jwtRequest.setFirstName(request.getFirstName());
        jwtRequest.setLastName(request.getLastName());
        jwtRequest.setPhoneNumber(request.getPhoneNumber());
        jwtRequest.setEmail(request.getEmail());
        jwtRequest.setStatus(new Parameter(10L));
        jwtRequest.setRoleId(new Parameter(12L));
        java.util.Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        jwtRequest.setUpdateDate(timestamp);
        jwtRequest.setPassword(request.getPassword());

        if (Objects.equals(id,null)) {
            return jwtUserDetailsService.createUser(jwtRequest);
        }else{
            return jwtUserDetailsService.updateUserGeneral(jwtRequest,id);
        }
    }
}
