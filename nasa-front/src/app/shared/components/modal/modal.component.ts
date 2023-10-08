import { Component, ElementRef, OnInit, OnDestroy, ChangeDetectionStrategy, Input, inject } from '@angular/core';
import { ModalService } from '../../../services/modal.service';

@Component({
  selector: 'app-modal',
  templateUrl: 'modal.component.html',
  styleUrls: ['modal.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ModalComponent implements OnInit, OnDestroy {
  private modalService = inject(ModalService);

  @Input() title = '';
  @Input() text = '';
  @Input() actionUrl = '';

  private element: any;

    constructor(private el: ElementRef) {
        this.element = el.nativeElement;
    }

    ngOnInit() {
        document.body.appendChild(this.element);

        this.element.addEventListener('click', (el: any) => {
            if (el.target.className === 'confirmation-modal') {
                this.close();
            }
        });
    }

    ngOnDestroy() {
      this.element.remove();
    }

    // open() {
    //     this.element.style.display = 'block';
    //     document.body.classList.add('confirmation-modal-open');
    // }

    close() {
        this.element.style.display = 'none';
        document.body.classList.remove('confirmation-modal-open');
    }
  
  onOverlayClick(event: MouseEvent): void {
    // Si l'element clicat és l'overlay (i no el contingut del modal), tancar el modal
    if ((event.target as Element).classList.contains('modal-overlay')) {
        this.closeModal();
    }
}

closeModal(): void {
  this.modalService.closeModal();
}

}
