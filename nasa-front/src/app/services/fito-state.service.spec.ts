/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { phytoStateService } from './phyto-state.service';

describe('Service: phytoState', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [phytoStateService]
    });
  });

  it('should ...', inject([phytoStateService], (service: phytoStateService) => {
    expect(service).toBeTruthy();
  }));
});
