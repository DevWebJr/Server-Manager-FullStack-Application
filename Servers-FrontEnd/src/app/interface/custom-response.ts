// communicates with the Response class in the back end

import { Server } from "./server";

export interface CustomResponse {
  timeStamp: Date;
  statusCode: number;
  status: string;
  reason: string;
  message: string;
  developerMessage: string;
  data: { servers?: Server[], server?: Server}
}
