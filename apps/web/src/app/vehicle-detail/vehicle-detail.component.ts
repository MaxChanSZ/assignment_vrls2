import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Vehicle } from '../vehicle.model';
import {DATE_PIPE_DEFAULT_OPTIONS, DatePipe, NgIf} from "@angular/common";

@Component({
  selector: 'app-vehicle-detail',
  standalone: true,
  providers: [
    { provide: DATE_PIPE_DEFAULT_OPTIONS, useValue: { dateFormat: 'dd MMM yyyy' } }
  ],
  templateUrl: './vehicle-detail.component.html',
  styleUrls: ['./vehicle-detail.component.css'],
  imports: [
    NgIf,
    DatePipe
  ]
})
export class VehicleDetailComponent {

  private _vehicle: any;

  @Input()
  set vehicle(val: any) {
    if (val) {
      this._vehicle = {
        ...val,
        startDate: val.startDate ? new Date(val.startDate) : null,
        endDate: val.endDate ? new Date(val.endDate) : null
      };
    } else {
      this._vehicle = null;
    }
  }
  @Output() close = new EventEmitter<void>();
  onCloseClicked() {
    this.close.emit();
  }

  get vehicle(): any {
    return this._vehicle;
  }
}
