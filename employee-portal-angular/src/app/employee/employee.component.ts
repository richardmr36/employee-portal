import { EmployeeDataService } from './../service/employee-data.service';
import { Employee } from './../model/employee';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  employee: Employee;

  constructor(private employeeDataService: EmployeeDataService, private router: Router) { }

  ngOnInit() {
    this.employee = new Employee(0, '', '', '', new Date((new Date()).getFullYear() - 20 + '-01-01T00:00:00'), '');
  }

  registerEmployee() {
    this.employeeDataService.registerEmployee(this.employee).subscribe(
      response => {
        this.router.navigate(['list-employees']);
      },
      error => {
        alert(error.statusText);
      }
    );
  }

}
