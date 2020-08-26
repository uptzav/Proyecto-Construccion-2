﻿using InfoCodiv19.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using System.Web.Script.Serialization;

namespace InfoCodiv19.Controllers
{
    public class DonacionController : Controller
    {
        // GET: Donacion

        Donacion d = new Donacion(); 
        public async Task<ActionResult> Index()
        {
            var lista = await d.Get_Donaciones();
            return View(lista);
        }

        public async Task<ActionResult> Get_Donaciones()
        {
            var lista = await d.Get_Donaciones();
          
            return Json(lista, JsonRequestBehavior.AllowGet);

        }

        public void Notificacion(Notificacion o)
        {
            
            string response = "";
            try
            {
                string titulo = o.titulo;
                string mensaje = o.mensaje;//gracnas
                string applicationID = "AAAAoGkP7EY:APA91bHF4ZxZmyKSWkOq9D4LXA4sDTW_7Dw2bkAvJm7ATbrvVAdEXGpY3zcZAvLuuTCKPRqmKYQrqoLC_32OQTP8t4W4CFKP-YVSUzRomZ3Wg7Lk5qvFjqnJdeT-CNRDjcfLrEnOgsl2";
                string senderId = "688957418566";
                //token de usuario
                //   string deviceId = "dPy0QisHT_Ksm3snK-GonO:APA91bG8j2n4rJvB8INUoaYhRnb8vSXrmRRS7p6rgmP7KZ9PVORB1Cx0fusVzWtu2_J7g0wZnCIif1Qsx9bqwOTu_Cytu2gMI1vDyWuLfxlFDBtpKi87JeQQHfx3ERnw_dC9zSfTY41C";
                WebRequest tRequest = WebRequest.Create("https://fcm.googleapis.com/fcm/send");
                tRequest.Method = "post";
                tRequest.ContentType = "application/json";
                var data = new
                {
                    to = o.token,
                    notification = new
                    {
                        body = mensaje,
                        title = titulo,
                        sound = "Enabled"
                    }
                };

                var serializer = new JavaScriptSerializer();
                var json = serializer.Serialize(data);
                Byte[] byteArray = Encoding.UTF8.GetBytes(json);
                tRequest.Headers.Add(string.Format("Authorization: key={0}", applicationID));
                tRequest.Headers.Add(string.Format("Sender: id={0}", senderId));
                tRequest.ContentLength = byteArray.Length;

                using (Stream dataStream = tRequest.GetRequestStream())
                {
                    dataStream.Write(byteArray, 0, byteArray.Length);
                    using (WebResponse tResponse = tRequest.GetResponse())
                    {
                        using (Stream dataStreamResponse = tResponse.GetResponseStream())
                        {
                            using (StreamReader tReader = new StreamReader(dataStreamResponse))
                            {
                                String sResponseFromServer = tReader.ReadToEnd();
                                string str = sResponseFromServer;
                                response = sResponseFromServer;
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                string str = ex.Message;
            }

        }


        public async Task<ActionResult> Modificar(Modificar o)
        {

          //  var lista = await d.Get_Donaciones();
            
            string key = o.key;
            string estado = o.estado;
            await    d.Modificar(key, estado);
            //var datos = lista.Where(a => a.key_donacion == key).FirstOrDefault();


            return Json("modificado",JsonRequestBehavior.AllowGet);
        }
    }
}