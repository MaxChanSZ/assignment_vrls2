import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Vehicle } from '../vehicle.model';
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-vehicle-detail',
  standalone: true,
  templateUrl: './vehicle-detail.component.html',
  styleUrls: ['./vehicle-detail.component.css'],
  imports: [
    NgIf
  ]
})
export class VehicleDetailComponent {
  @Input() vehicle: any;
  @Output() close = new EventEmitter<void>();
  onCloseClicked() {
    this.close.emit();
  }
}
