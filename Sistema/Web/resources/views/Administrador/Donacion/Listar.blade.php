{{--$donaciones--}}
@extends('Shared._Layout')

@section('header')
    <h1>
        Donaciones Registradas
    </h1>
@endsection

@section('content')
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Lista de Donaciones Registradas</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>TIPO DE DONACION</th>
                            <th>CALIDAD DE ALIMENTOS</th>
                            <th>CANTIDAD</th>
                            <th>DIRECCIÓN</th>
                            <th>NOMBRE DE DONANTE</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($donaciones as $item)
                            <tr>
                                <td>{{$item->tipoDonacion}}</td>
                                <td>{{$item->calidadAlimentos}}</td>
                                <td>{{$item->cantidad}}</td>
                                <td>{{$item->direccion}}</td>
                                <td>{{$item->nombreDonante}}</td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
@endsection
