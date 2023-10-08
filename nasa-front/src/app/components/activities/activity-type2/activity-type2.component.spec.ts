/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { ActivityType2Component } from './activity-type2.component';

describe('ActivityType2Component', () => {
  let component: ActivityType2Component;
  let fixture: ComponentFixture<ActivityType2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityType2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityType2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
