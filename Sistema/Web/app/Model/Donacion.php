<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Http\Request;

class Donacion extends Model
{
    protected $table = "tbl_donacion";
    protected $primaryKey = "idDonacion";
    protected $fillable = [
        'tipoDonacion',
        'calidadAlimentos',
        'cantidad',
        'direccion',
        'nombreDonante',
    ];
    public $timestamps = false;

    public static function DonacionRegistrar(Request $request)
    {
        $obj = new Donacion();
        $obj->tipoDonacion = $request->input('tipoDonacion');
        $obj->calidadAlimentos = $request->input('calidadAlimentos');
        $obj->cantidad = $request->input('cantidad');
        $obj->direccion = $request->input('direccion');
        $obj->nombreDonante = $request->input('nombreDonante');
        $obj->save();
    }
}
