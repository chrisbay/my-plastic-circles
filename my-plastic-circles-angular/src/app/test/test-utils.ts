import { of } from "rxjs";
import { Disc } from "../model/disc";
import { DiscService } from "../service/disc.service";
import { ManufacturerService } from "../service/manufacturer.service";

export class TestMocks {

  private static testManufacturer = { id: 2, name: 'Discraft'};

  private static testDiscs: Disc[] = [{
    id: 1,
    model: 'Avenger SS',
    manufacturer: TestMocks.testManufacturer,
    speed: 11,
    glide: 5,
    turn: -3,
    fade: 1,
    notes: '',
    favorite: false
  }];

  private static manufacturerServiceStub: Partial<ManufacturerService> = {
    getAll: () => of([TestMocks.testManufacturer])
  };

  private static discServiceStub: Partial<DiscService> = {
    getAll: () => of(TestMocks.testDiscs)
  };

  static getDiscServiceMock(): Partial<DiscService> {
    return TestMocks.discServiceStub;
  }

  static getManufacturerServiceMock(): Partial<ManufacturerService> {
    return TestMocks.manufacturerServiceStub;
  }

}

export const customMatchers = {

  deepEquals: (matchersUtil) => {
    return {
      compare: (actual, expected) => {
        return {
          pass: matchersUtil(actual, expected)
        };
      }
    };
  }

};