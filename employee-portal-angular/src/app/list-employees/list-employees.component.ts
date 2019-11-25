import { EmployeeDataService } from './../service/employee-data.service';
import { Employee } from './../model/employee';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-employees',
  templateUrl: './list-employees.component.html',
  styleUrls: ['./list-employees.component.css']
})
export class ListEmployeesComponent implements OnInit {

  employees: Employee[];

  constructor(private employeeDataService: EmployeeDataService) { }

  ngOnInit() {
    this.refreshEmployees();
  }

  refreshEmployees() {
    this.employeeDataService.listEmployees().subscribe(
      response => {
        this.employees = response;
      },
      error => {
        alert(error.statusText);
      }
    );
  }

}
