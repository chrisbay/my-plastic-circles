export interface Message {
  type: MessageType;
  message: string;
}

export enum MessageType {
  Success = 'success',
  Info = 'info',
  Warning = 'warning',
  Error = 'error'
}