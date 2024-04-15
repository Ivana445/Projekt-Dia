import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-progress-circle',
  standalone: true,
  imports: [],
  templateUrl: './progress-circle.component.html',
  styleUrl: './progress-circle.component.scss'
})
export class ProgressCircleComponent {

  @Input()
  percentage='';
}
