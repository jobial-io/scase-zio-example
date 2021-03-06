/*
 * Copyright (c) 2020 Jobial OÜ. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with
 * the License. A copy of the License is located at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package io.jobial.scase.example.greeting.zio

import io.jobial.sclap.zio.ZIOCommandLineApp
import zio._
import zio.interop.catz._
import zio.interop.catz.implicits._
import zio.console._

object GreetingZIOAppExample extends ZIOCommandLineApp with GreetingServiceConfig {

  def run =
    for {
      (service, client) <- greetingServiceConfig.serviceAndClient[Task](new GreetingService {})
      _ <- service.start
      helloResponse <- client ? Hello("world")
      _ <- putStr(helloResponse.sayingHello)
    } yield ()
}
