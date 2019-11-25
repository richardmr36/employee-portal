import { BASE_API_URL } from './../app.constants';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee } from '../model/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeDataService {

  constructor(private httpClient: HttpClient) { }

  listEmployees() {
    return this.httpClient.get<Employee[]>(`${BASE_API_URL}/employees`);
  }

  registerEmployee(employee) {
    return this.httpClient.post(`${BASE_API_URL}/employees`, employee);
  }
}
