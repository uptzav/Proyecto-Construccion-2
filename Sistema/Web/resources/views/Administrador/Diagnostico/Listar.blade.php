@extends('Shared._Layout')

@section('header')
    <h1>
        Diagnosticos Registrados
    </h1>
@endsection

@section('content')
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Lista de Diagnosticos Registrados</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>EMAIL</th>
                            <th>TEMPERATURA CORPORAL</th>
                            <th>SENSACION DEL MOMENTO</th>
                            <th>TIENE FIEBRE?</th>
                            <th>TIENE TOS?</th>
                            <th>FATIGA</th>
                            <th>FALTA DE ALIENTO</th>
                            <th>DONDE SE ENCUENTRA</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($diagnosticos as $item)
                            <tr>
                                <td>{{$item->email}}</td>
                                <td>{{$item->temperaturaCorporal}}</td>
                                <td>{{$item->sentirseAhora}}</td>
                                <td>{{$item->tienefiebre}}</td>
                                <td>{{$item->tieneTos}}</td>
                                <td>{{$item->fatiga}}</td>
                                <td>{{$item->faltaAlimento}}</td>
                                <td>{{$item->dondeEstaAhora}}</td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
@endsection
