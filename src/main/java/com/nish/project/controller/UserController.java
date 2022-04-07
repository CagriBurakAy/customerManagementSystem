package com.nish.project.controller;

import com.nish.project.model.customer.Customer;
import com.nish.project.model.customerFiles.CustomerFiles;
import com.nish.project.model.files.Files;
import com.nish.project.payload.Response;
import com.nish.project.service.CustomerFilesService;
import com.nish.project.service.CustomerService;
import com.nish.project.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerFilesService customerFilesService;
    @Autowired
    private FilesService filesService;

    @GetMapping("/getAllCustomer")
    public List<Customer> list() {
        return customerService.listAll();
    }

    @PostMapping("/addCustomer")
    public String add(@RequestBody Customer customer) {
        customerService.save(customer);
        return "New Customer Added";
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> get(@PathVariable Integer id) {
        try {
            Customer customer = customerService.get(id);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateCustomer/{id}")
    public String update(@RequestBody Customer customer, @PathVariable Integer id) {
        try {
            customerService.update(customer, id);
            return "Customer " + id + " updated.";

        } catch (NoSuchElementException e) {
            return "Customer " + id + " could not update.";
        }
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            customerService.delete(id);
            return "Deleted Customer with id " + id;

        } catch (NoSuchElementException e) {
            return "Customer " + id + " could not delete.";
        }
    }


    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int fileName, HttpServletRequest request) throws FileNotFoundException {
        // Load file as Resource
        Files databaseFile = filesService.getFile(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));
    }

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        Files fileName = filesService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new Response(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/deleteFile/{id}")
    public String deleteFileById(@PathVariable int id) {
        try {

            filesService.deleteFile(id);
            return "Deleted File with id " + id;
        } catch (NoSuchElementException e) {
            return "File " + id + " could not delete.";
        }
    }

    @GetMapping("/getAllFiles")
    public List<Files> Filelist() {
        return filesService.listAll();
    }

    @PutMapping("/files/{id}")
    public String updatefile(@RequestParam("file") MultipartFile file, @PathVariable int id) {
        try {
            filesService.storeFile2(file, id);
            return "Updated File with id " + id;
        } catch (NoSuchElementException e) {
            return "File " + id + " could not update.";
        }
    }

    @GetMapping("/getAllCustomerFiles")
    public List<CustomerFiles> customerFilesList() {
        return customerFilesService.listAll();
    }

    @PostMapping("/addCustomerFile")
    public String addCustomerFile(@RequestBody CustomerFiles customerFiles) {
        try {
            customerFilesService.save(customerFiles);
            return "New Custormer File Added";
        } catch (NoSuchElementException e) {
            return "Customer File could not update.";
        }
    }

    @GetMapping("/getCustomerFile/{customerId}")
    public List<CustomerFiles> getCustomerFile(@PathVariable Integer customerId) {

        List<CustomerFiles> customerFiles = customerFilesService.get(customerId);
        return customerFiles;

    }

    @PutMapping("/updateCustomerFile/{id}")
    public String updateCustomerFileById(@RequestBody CustomerFiles customerFiles, Integer id) {

        try {
            customerFilesService.update(customerFiles, id);
            return "Updated Customer File ";

        } catch (NoSuchElementException e) {
            return "Could not Update Customer File";
        }

    }

    @GetMapping("/getCustomerFileById/{Id}")
    public CustomerFiles getCustomerFileById(@PathVariable Integer Id) {
        CustomerFiles customerFiles = customerFilesService.getbyId(Id);
        return customerFiles;
    }

    @DeleteMapping("/deleteCustomerFile/{id}")
    public String deleteCustomerById(@PathVariable int id) {
        try {

            customerFilesService.delete(id);
            return "Deleted CustomerFile with id " + id;
        } catch (NoSuchElementException e) {
            return "Customer File " + id + " could not delete.";
        }
    }
}