import { Manufacturer } from "../manufacturers/manufacturer";

export interface Disc {
  id: number | null;
  model: string;
  manufacturer: Manufacturer;
  speed: number;
  glide: number;
  turn: number;
  fade: number;
  notes: string;
  favorite: boolean;
}

export interface DiscResolved {
  disc: Disc | null;
  error?: string;
}