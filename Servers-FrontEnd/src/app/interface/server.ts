import {Status} from "../enum/status.enum";

// communicates with the Server class in the back end
export interface Server {
  id: number;
  ipAddress: string;
  name: string;
  memory: string;
  type: string;
  imageUrl: string;
  status: Status;
}
